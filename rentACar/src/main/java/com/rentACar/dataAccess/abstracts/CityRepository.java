package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.City;

public interface CityRepository extends JpaRepository<City, Integer>{

	City findById(int id);
}
