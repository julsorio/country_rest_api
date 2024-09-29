package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class DemoController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping(path = "/countries")
	public ResponseEntity<List<Country>> getCountries() {
		return new ResponseEntity<List<Country>>(countryService.getCountries(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/country/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
		Optional<Country> country = countryService.getCountryById(id);
		if(country.isEmpty()) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Country>(country.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping(path = "/country")
	public ResponseEntity<Country> saveCountry(@RequestBody Country country) {
		Country savedCountry = null;
		
		try {
			savedCountry = countryService.saveCountry(country);
			return new ResponseEntity<Country>(savedCountry, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path = "/country/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
		Optional<Country> savedCountry = null;
		
		try {
			savedCountry = countryService.updateCountry(id, country);
			if(savedCountry.isEmpty()) {
				return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Country>(savedCountry.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/country/{id}")
	public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
		Optional<Country> savedCountry = null;
		
		try {
			savedCountry = countryService.getCountryById(id);
			if(savedCountry.isEmpty()) {
				return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
			} else {
				countryService.deleteCountry(id);
				return new ResponseEntity<Country>(HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
