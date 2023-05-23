package com.dao;

import java.util.List;

import com.entity.Category;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class CategoryDAOImpl implements CategoryDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public void insertCategory(Category category) {
		initRoutine();
		em.persist(category);
		closeRoutine();

	}

	@Override
	public void updateCategory(Category category) {
		initRoutine();
		em.merge(category);
		closeRoutine();
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		initRoutine();
		em.remove(em.find(Category.class, categoryId));
		closeRoutine();

	}

	@Override
	public List<Category> readCategory() {
		initRoutine();
		List<Category> c = em.createNamedQuery("Category.findAll", Category.class).getResultList();
		closeRoutine();
		return c;
	}

	private void initRoutine() {
		emf = ProviderManager.getEntityManagerFactory();
		em = ProviderManager.getEntityManager(emf);
		ProviderManager.beginTransaction(em);
	}

	private void closeRoutine() {
		try {
			ProviderManager.commitTransaction(em);
		} catch (RollbackException rbe) {
			System.err.println("Transazione fallita: eseguo il rollback.");
			rbe.printStackTrace();
			ProviderManager.rollbackTransaction(em);
		} finally {
			ProviderManager.closeTransaction(em);
			ProviderManager.closeEntityManagerFactory(emf);
		}
	}

}
