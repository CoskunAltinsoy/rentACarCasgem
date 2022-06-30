package com.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentACar.entities.concretes.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	Invoice findByid(int id);
}
