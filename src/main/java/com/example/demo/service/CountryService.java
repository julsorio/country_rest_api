package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Country;

public interface CountryService {

	public List<Country> getCountries();
	
	public Optional<Country> getCountryById(Long id);
	
	public Country saveCountry(Country country);
	
	public Optional<Country> updateCountry(Long id, Country country);
	
	public void deleteCountry(Long id);
}
