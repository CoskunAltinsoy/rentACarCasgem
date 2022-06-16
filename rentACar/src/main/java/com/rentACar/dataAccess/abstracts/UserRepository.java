package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(int id);
}
