package com.rentACar.business.responses.individualCustomers;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualCustomerResponse {

	private int id;	
	private String firstName;
	private String lastName;	
	private String nationalIdentity;	
	private String email;	
	private String password;
}
