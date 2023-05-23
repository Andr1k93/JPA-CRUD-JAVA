package com.dao;

import java.util.List;

import com.entity.City;
import com.entity.Country;

public interface CountryCityDAO {

	public void insertCountryCity(Country country, List<City> cities);

	public void deleteCountry(Integer countryId);

	public void updateCountry(Country country);

	public List<City> getAllCities();

	public List<Country> getAllCountries();

}
