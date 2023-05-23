package com.dao;

import java.util.List;

import com.entity.City;
import com.entity.Country;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class CountryCityDAOImpl implements CountryCityDAO {

	private EntityManager em;
	private EntityManagerFactory emf;

	@Override
	public void insertCountryCity(Country country, List<City> cities) {
		initRoutine();

		em.persist(country);

		for (City c : cities) {
			c.setCountry(country);
			em.persist(c);
		}
		closingRoutine();

	}

	@Override
	public void deleteCountry(Integer countryId) {
		initRoutine();

		em.remove(em.find(Country.class, countryId));

		closingRoutine();

	}

	@Override
	public void updateCountry(Country country) {
		initRoutine();

		em.merge(country);

		closingRoutine();
	}

	@Override
	public List<City> getAllCities() {
		initRoutine();

		List<City> cities = em.createNamedQuery("City.findAll", City.class).getResultList();

		closingRoutine();
		return cities;
	}

	@Override
	public List<Country> getAllCountries() {
		initRoutine();

		List<Country> countries = em.createNamedQuery("Country.findAll", Country.class).getResultList();

		closingRoutine();
		return countries;
	}

	private void initRoutine() {
		emf = ProviderManager.getEntityManagerFactory();
		em = ProviderManager.getEntityManager(emf);
		ProviderManager.beginTransaction(em);
	}

	private void closingRoutine() {
		try {
			ProviderManager.commitTransaction(em);
		} catch (RollbackException rbe) {
			System.err.println("Transazione fallita");
			rbe.printStackTrace();
			ProviderManager.rollbackTransaction(em);
		} finally {
			ProviderManager.closeTransaction(em);
			ProviderManager.closeEntityManagerFactory(emf);
		}
	}

}
