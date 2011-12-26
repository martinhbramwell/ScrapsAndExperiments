package com.foo.server.dao.jpa;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.foo.shared.domain.EntidadBase;

/**
 * <p>Helper JPA Class.</p>
 * Clase helper. No es necesario extender de esta clase.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com tomado y con
 *         algunas modificaciones de <a href=
 *         "http://www.javacodegeeks.com/2010/05/gwt-2-spring-3-jpa-2-hibernate-35.html"
 *         >gwt-2-spring-3-jpa-2-hibernate-35</a>
 * 
 * @param <K>
 *            Clase de la clave
 * @param <E>
 *            Entidad
 * @since 0.1.0
 */

public abstract class JpaDAO<K, E extends EntidadBase> extends JpaDaoSupport {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	protected void init() {
		setEntityManager(entityManager);
	}

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public JpaDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
		getJpaTemplate().persist(entity);
	}

	public void remove(E entity) {
		if (getJpaTemplate().contains(entity))
			getJpaTemplate().remove(entity);
		else
			getJpaTemplate().remove(
					getJpaTemplate().getReference(entity.getClass(),
							entity.getId()));
	}

	// Pongo privado estos dos metodos para simplificar.
	private E merge(E entity) {
		return getJpaTemplate().merge(entity);
	}

	// private void refresh(E entity) {
	// getJpaTemplate().refresh(entity);
	// }

	public E findById(K id) {
		return getJpaTemplate().find(entityClass, id);
	}

	public E flush(E entity) {
		getJpaTemplate().flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		@SuppressWarnings("rawtypes")
		Object res = getJpaTemplate().execute(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query q = em.createQuery("SELECT h FROM "
						+ entityClass.getName() + " h");
				return q.getResultList();
			}

		});

		return (List<E>) res;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer removeAll() {
		return (Integer) getJpaTemplate().execute(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query q = em.createQuery("DELETE FROM " + entityClass.getName()
						+ " h");
				return q.executeUpdate();
			}

		});
	}

	/**
	 * Salva o actualiza seg&uacute;n corresponda la entidad. Implementa lo
	 * siguiente: <h3>The pattern</h3>
	 * <p>
	 * So where does all this leave us? The rule of thumb I stick to is this:
	 * </p>
	 * <ul>
	 * <li>When and only when (and preferably where) we create a new entity,
	 * invoke <tt>EntityManager.persist</tt> to save it. This makes perfect
	 * sense when we view our <a href=
	 * "http://domaindrivendesign.org/discussion/messageboardarchive/Repositories.html"
	 * >domain access objects as collections</a>. I call this the
	 * <em>persist-on-new pattern</em>.</li>
	 * <li>When updating an existing entity, we do not invoke any
	 * <tt>EntityManager</tt> method; the JPA provider will automatically update
	 * the database at flush or commit time.</li>
	 * 
	 * <li>When we receive an updated version of an existing simple entity (an
	 * entity with no references to other entities) from outside of our
	 * application and want to save the new state, we invoke
	 * <tt>EntityManager.merge</tt> to copy that state into the persistence
	 * context. Because of the way merging works, we can also do this if we are
	 * unsure whether the object has been already persisted.</li>
	 * <li>When we need more control over the merging process, we use the
	 * <em>DIY merge pattern</em>.</li>
	 * </ul>
	 * <h3>The problem with merge (<em>DIY merge pattern</em>)</h3>
	 * <p>
	 * Before we continue, we need to discuss one disadvantage of the way
	 * <tt>EntityManager.merge</tt> works; it can easily break bidirectional
	 * associations. Consider the example with the <tt>Order</tt> and
	 * <tt>OrderLine</tt> classes from <a href=
	 * "/2009/03/16/jpa-implementation-patterns-bidirectional-assocations/">the
	 * previous blog in this series</a>. If an updated <tt>OrderLine</tt> object
	 * is received from a web front end (or from a Hessian client, or a Flex
	 * application, etc.) the <tt>order</tt> field might be set to <tt>null</tt>
	 * . If that object is then merged with an already loaded entity, the
	 * <tt>order</tt> field of that entity is set to <tt>null</tt>. But it won't
	 * be removed from the <tt>orderLines</tt> set of the <tt>Order</tt> it used
	 * to refer to, thereby breaking the invariant that every element in an
	 * <tt>Order</tt>'s <tt>orderLines</tt> set has its <tt>order</tt> field set
	 * to point back at that <tt>Order</tt>.
	 * </p>
	 * 
	 * <p>
	 * In this case, or other cases where the simplistic way
	 * <tt>EntityManager.merge</tt> copies the object state into the loaded
	 * entity causes problems, we can fall back to the
	 * <em>DIY merge pattern</em>. Instead of invoking
	 * <tt>EntityManager.merge</tt> we invoke <a href=
	 * "http://java.sun.com/javaee/5/docs/api/javax/persistence/EntityManager.html#find%28java.lang.Class,%20java.lang.Object%29"
	 * ><tt>EntityManager.find</tt></a> to find the existing entity and copy
	 * over the state ourselves. If <tt>EntityManager.find</tt> returns
	 * <tt>null</tt> we can decide whether to persist the received object or
	 * throw an exception. Applied to the <tt>Order</tt> class this pattern
	 * could be implemented like this:
	 * </p>
	 * 
	 * <pre class="java">
	 * &nbsp;
	 * 	Order existingOrder = dao.<span style="color: rgb(0, 102, 0);">findById</span><span style="color: rgb(102, 204, 102);">(</span>receivedOrder.<span style="color: rgb(0, 102, 0);">getId</span><span style="color: rgb(102, 204, 102);">(</span><span style="color: rgb(102, 204, 102);">)</span><span style="color: rgb(102, 204, 102);">)</span>;
	 * 	<span style="color: rgb(177, 177, 0);">if</span><span style="color: rgb(102, 204, 102);">(</span>existingOrder == <span style="color: rgb(0, 0, 0); font-weight: bold;">null</span><span style="color: rgb(102, 204, 102);">)</span> <span style="color: rgb(102, 204, 102);">{</span>
	 * 
	 * 		dao.<span style="color: rgb(0, 102, 0);">persist</span><span style="color: rgb(102, 204, 102);">(</span>receivedOrder<span style="color: rgb(102, 204, 102);">)</span>;
	 * 	<span style="color: rgb(102, 204, 102);">}</span> <span style="color: rgb(177, 177, 0);">else</span> <span style="color: rgb(102, 204, 102);">{</span>
	 * 		existingOrder.<span style="color: rgb(0, 102, 0);">setCustomerName</span><span style="color: rgb(102, 204, 102);">(</span>receivedOrder.<span style="color: rgb(0, 102, 0);">getCustomerName</span><span style="color: rgb(102, 204, 102);">(</span><span style="color: rgb(102, 204, 102);">)</span><span style="color: rgb(102, 204, 102);">)</span>;
	 * 		existingOrder.<span style="color: rgb(0, 102, 0);">setDate</span><span style="color: rgb(102, 204, 102);">(</span>receivedOrder.<span style="color: rgb(0, 102, 0);">getDate</span><span style="color: rgb(102, 204, 102);">(</span><span style="color: rgb(102, 204, 102);">)</span><span style="color: rgb(102, 204, 102);">)</span>;
	 * 	<span style="color: rgb(102, 204, 102);">}</span>
	 * 
	 * &nbsp;
	 * </pre>
	 * 
	 * <b>Fuente</b> <a href=
	 * "http://blog.xebia.com/2009/03/23/jpa-implementation-patterns-saving-detached-entities/"
	 * >JPA implementation patterns: Saving (detached) entities</a>
	 * 
	 * @param entity
	 * @return la entidad. En caso de persistencia retorna con el id asociado.
	 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
	 */
	public E saveOrUpdate(E entity) {
		if (entity.getId() == null) {
			getJpaTemplate().persist(entity);
			return entity;
		}
		if (!getJpaTemplate().getEntityManager().contains(entity))
			return merge(entity);
		return entity;
	}

}