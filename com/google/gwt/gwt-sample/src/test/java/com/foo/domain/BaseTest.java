package com.foo.domain;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public abstract class BaseTest extends TestCase {

	private static Map<String, EntityManagerFactory> emfPUs = new HashMap<String, EntityManagerFactory>();

	//private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public EntityManagerFactory getEMFactory() {
		EntityManagerFactory emfPU = emfPUs.get(getPersistenceUnitName());

		if (emfPU == null) {
			emfPU = Persistence
					.createEntityManagerFactory(getPersistenceUnitName());
			emfPUs.put(getPersistenceUnitName(), emfPU);
		}
		return emfPU;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		if (hayQueCearEntityManager()) {
			crearEntityManager();
		}
	}

	protected String getPersistenceUnitName() {
		return "embalaje";
	}

	protected boolean hayQueCearEntityManager() {
		return true;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private void crearEntityManager() {
		entityManager = getEMFactory().createEntityManager();
	}

	@Override
	protected void tearDown() throws Exception {
		if (hayQueCearEntityManager()) {
			entityManager.close();
			emfPUs.get(getPersistenceUnitName()).close();
			emfPUs.clear();
		}
	}

}
