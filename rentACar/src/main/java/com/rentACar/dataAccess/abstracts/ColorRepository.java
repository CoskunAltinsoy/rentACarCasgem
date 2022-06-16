package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>{

	Color findById(int id);
}
