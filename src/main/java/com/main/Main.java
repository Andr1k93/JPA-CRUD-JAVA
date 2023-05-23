package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.CategoryDAO;
import com.dao.CategoryDAOImpl;
import com.dao.CountryCityDAO;
import com.dao.CountryCityDAOImpl;
import com.dao.FilmActorDAO;
import com.dao.FilmActorDAOImpl;
import com.entity.Actor;
import com.entity.Category;
import com.entity.City;
import com.entity.Country;
import com.entity.Film;

public class Main {

	public static void main(String[] args) {
		CategoryDAO dao = new CategoryDAOImpl();
		dao.insertCategory(new Category("nuova categoria"));
		dao.insertCategory(new Category("nuova categoria 2"));

		dao.updateCategory(new Category(18, "categoria 1 modificata"));

		dao.deleteCategory(17);
		dao.readCategory().forEach(System.out::println);
		;

		CountryCityDAO dao1 = new CountryCityDAOImpl();

		Country country1 = new Country("NuovoCountry");

		List<City> cities = new ArrayList<>();

		City city1 = new City("Nuova Citta1");
		City city2 = new City("Nuova Citta2");

		cities.add(city1);
		cities.add(city2);

		dao1.insertCountryCity(country1, cities);

		country1 = new Country("Country modificato");
		dao1.updateCountry(country1);
		dao1.getAllCities().forEach(System.out::println);

		// rimozione Country

		dao1.deleteCountry(110);

		FilmActorDAO filmActorDAO = new FilmActorDAOImpl();

		Map<Film, List<Actor>> filmActorMap = new HashMap<>();

		Film film1 = new Film("Nuovofilm", "bello");
		Film film2 = new Film("NuovoFilm2", "divertente");

		List<Actor> film1ActorsList = new ArrayList<>();
		List<Actor> film2ActorsList = new ArrayList<>();

		Actor a = new Actor("A", "A");
		Actor b = new Actor("B", "B");
		Actor c = new Actor("C", "C");

		film1ActorsList.add(a);
		film1ActorsList.add(b);

		film2ActorsList.add(b);
		film2ActorsList.add(c);

		filmActorMap.put(film1, film1ActorsList);
		filmActorMap.put(film2, film2ActorsList);

		filmActorDAO.insertFilmActor(filmActorMap);

		filmActorDAO.deleteActorWithAnnotation(202);

		filmActorDAO.deleteFilm(1002);

	}

}
