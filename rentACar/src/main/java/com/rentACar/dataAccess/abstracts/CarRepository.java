package com.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{

	Car findById(int id);
	List<Car> findByBrandId(int id);
}
