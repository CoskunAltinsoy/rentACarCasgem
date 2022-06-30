package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer>{

	CorporateCustomer findById(int id);
}
