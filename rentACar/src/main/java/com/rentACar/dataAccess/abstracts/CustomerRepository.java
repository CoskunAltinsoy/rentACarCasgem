package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findById(int id);
}
