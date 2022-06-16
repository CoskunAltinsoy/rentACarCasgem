package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer>{

	Maintenance findById(int id);
}
