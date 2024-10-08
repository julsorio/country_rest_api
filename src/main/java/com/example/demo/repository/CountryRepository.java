package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

}
