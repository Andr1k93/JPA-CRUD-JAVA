package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;

/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Integer filmId;

	private String title;

	@Lob
	private String description;

	@Column(name = "language_id")
	private Integer languageId;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	private Integer length;

	@Column(name = "original_language_id")
	private Integer originalLanguageId;

	private String rating;

	@Column(name = "release_year")
	private Integer releaseYear;

	@Column(name = "rental_duration")
	private Integer rentalDuration;

	@Column(name = "rental_rate")
	private Double rentalRate;

	@Column(name = "replacement_cost")
	private Double replacementCost;

	@Column(name = "special_features")
	private String specialFeatures;

	//bi-directional many-to-many association to Actor
	// Nell'entity padrona della relazione bisogna mettere l'annotazione @JoinTable
	// per dire al PP (Hibernate) come si chiama la tabella di congiunzione. Inoltre
	// all'interno di tale annotazione vanno specificate le PK di entrambe le entity coinvolte
	// usando l'annotazione @JoinColumn (vedi sotto)
	@ManyToMany
	@JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private List<Actor> actors = new ArrayList<>(); // Inizializzo qui la lista in modo tale che
													// quando ci faccio delle operazioni sopra
													// non risulti null

	public Film() {
	}

	// Costruttore per le insert
	public Film(String title, String description) {
		this.title = title;
		this.description = description;
		this.languageId = 1;
		this.length = 190;
		this.originalLanguageId = 1;
		this.rating = "R";
		this.releaseYear = 2023;
		this.rentalDuration = 4;
		this.rentalRate = 2.99;
		this.replacementCost = 20.99;
		this.specialFeatures = "Behind the Scenes";
	}

	// Costruttore per le update
	public Film(Integer filmId, String title, String description) {
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.languageId = 1;
		this.length = 190;
		this.originalLanguageId = 1;
		this.rating = "R";
		this.releaseYear = 2023;
		this.rentalDuration = 4;
		this.rentalRate = 2.99;
		this.replacementCost = 20.99;
		this.specialFeatures = "Behind the Scenes";
	}

	public Integer getFilmId() {
		return this.filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getOriginalLanguageId() {
		return this.originalLanguageId;
	}

	public void setOriginalLanguageId(Integer originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Double getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + "]";
	}

}