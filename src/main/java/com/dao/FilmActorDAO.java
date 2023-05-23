package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Actor;
import com.entity.Film;

public interface FilmActorDAO {

	// Le chiavi di questa mappa sono di tipo Film. I valori di questa mappa
	// sono di tipo List<Actor>, ovvero: ad ogni chiave Film corrisponde una lista
	// di Actor che ha recitato in quel Film
	public void insertFilmActor(Map<Film, List<Actor>> filmActorMap);

	// remove di un child che non usa l'annotation nell'entity
	public void deleteActor(Integer actorId);

	// usiamo l'annotazione @PreRemove nell'entity Actor
	public void deleteActorWithAnnotation(Integer actorId);

	public void deleteFilm(Integer filmId);

	public Film getFilm(Integer filmId);

	public Actor getActor(Integer actorId);

}
