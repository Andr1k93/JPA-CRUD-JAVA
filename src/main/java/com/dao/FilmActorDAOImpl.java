package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Actor;
import com.entity.Film;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class FilmActorDAOImpl implements FilmActorDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public void insertFilmActor(Map<Film, List<Actor>> filmActorMap) {
		initRoutine();

		for (Film f : filmActorMap.keySet()) {
			for (Actor a : filmActorMap.get(f)) {
				a.getFilms().add(f);
			}
			f.setActors(filmActorMap.get(f));
		}

		for (Film f : filmActorMap.keySet()) {
			for (Actor a : filmActorMap.get(f)) {
				em.persist(a);
			}
			em.persist(f);
		}

		closeRoutine();
	}

	@Override
	public void deleteActor(Integer actorId) {
		initRoutine();

		Actor actorToRemove = getActorPrivate(actorId);

		List<Film> tempFilms = actorToRemove.getFilms();
		for (Film f : tempFilms) {
			f.getActors().remove(actorToRemove);
		}
		// solo ora posso rimuovere il child.
		em.remove(actorToRemove);
		closeRoutine();
	}

	@Override
	public void deleteActorWithAnnotation(Integer actorId) {
		initRoutine();
		Actor actorToRemove = getActorPrivate(actorId);
		em.remove(actorToRemove);
		closeRoutine();

	}

	@Override
	public void deleteFilm(Integer filmId) {
		initRoutine();
		Film filmToRemove = getFilmPrivate(filmId);
		em.remove(filmToRemove);
		closeRoutine();
	}

	@Override
	public Film getFilm(Integer filmId) {
		initRoutine();
		Film film = em.find(Film.class, filmId);
		closeRoutine();
		return film;
	}

	@Override
	public Actor getActor(Integer actorId) {
		initRoutine();
		Actor actor = em.find(Actor.class, actorId);
		closeRoutine();
		return actor;
	}

	public Film getFilmPrivate(Integer filmId) {
		return em.find(Film.class, filmId);
	}

	public Actor getActorPrivate(Integer actorId) {
		return em.find(Actor.class, actorId);
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
