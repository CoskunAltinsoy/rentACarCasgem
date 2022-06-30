package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.OrderedAdditionalService;

public interface OrderedAdditionalServiceRepository extends JpaRepository<OrderedAdditionalService, Integer> {

	OrderedAdditionalService findById(int id);
}
