package com.foo.server.dao.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foo.server.dao.IProvinciaDAO;
import com.foo.shared.domain.Provincia;

/**
 * <p>
 * An example <a href=
 * "http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html"
 * >DAO</a> implementation.
 * </p>
 * DAO ejemplo de provincia basado en JPA.
 * 
 * @author Gardella Juan Pablo - gardellajuanpablo@gmail.com
 * @since 0.1.0
 * 
 */
@Repository
public class ProvinciaDAOImpl extends JpaDAO<Integer, Provincia> implements
		IProvinciaDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ProvinciaDAOImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public List<Provincia> findByName(String name) {

		logger.info("Consultando provincias con el nombre {}", name);

		return getJpaTemplate().find(
				"select p from Provincia p where p.nombre like ?1", name + "%");
		// List<Provincia> p = entityManager
		// .createQuery("select p from Provincia p where p.nombre like ?1")
		// .setParameter(1, name + "%").getResultList();

		// Lo hago porque sino falla la deserealizacion por RPC
		// Ver si usamos Gwt Gilead
		// Provincia.toArrayList(p);

		// return p;
	}

	@Override
	@Transactional
	public Provincia save(Provincia p) {
		return saveOrUpdate(p);
	}

}
