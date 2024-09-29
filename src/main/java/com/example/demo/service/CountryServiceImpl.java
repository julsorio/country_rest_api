package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> getCountries() {
		return (List<Country>) countryRepository.findAll();
	}

	@Override
	public Optional<Country> getCountryById(Long id) {
		return countryRepository.findById(id);
	}

	@Override
	public Country saveCountry(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public Optional<Country> updateCountry(Long id, Country country) {
		Optional<Country> optionalCountry = countryRepository.findById(id);
		if(optionalCountry.isPresent()) {
			Country existingCountry = optionalCountry.get();
			existingCountry.setName(country.getName());
			Country savedCountry = countryRepository.save(existingCountry);
			return Optional.of(savedCountry);
		} else {
			return Optional.empty();
		}
		
	}

	@Override
	public void deleteCountry(Long id) {
		countryRepository.deleteById(id);
	}
	
	
}
