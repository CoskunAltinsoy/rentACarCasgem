package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>{

	Rental findById(int id);
}
