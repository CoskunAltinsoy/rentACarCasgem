package com.rentACar.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="individual_customers")
@PrimaryKeyJoinColumn(name="customer_id")
public class IndividualCustomer extends Customer {

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "national_identity")
	private String nationalIdentity;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
}
