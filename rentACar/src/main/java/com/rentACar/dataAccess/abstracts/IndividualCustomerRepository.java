package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {

	IndividualCustomer findById(int id);
}
