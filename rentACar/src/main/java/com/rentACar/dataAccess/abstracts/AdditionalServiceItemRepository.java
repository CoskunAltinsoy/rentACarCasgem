package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.AdditionalServiceItem;

public interface AdditionalServiceItemRepository extends JpaRepository<AdditionalServiceItem, Integer>{

	AdditionalServiceItem findById(int id);
}
