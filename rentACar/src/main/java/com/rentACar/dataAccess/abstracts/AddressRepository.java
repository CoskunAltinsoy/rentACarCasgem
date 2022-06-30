package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Address findById(int id);
}
