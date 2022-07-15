package com.rentACar.business.responses.individualCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualCustomersResponse {

	private int id;	
	private String firstName;
	private String lastName;	
	private String nationalIdentity;	
	private String email;	
	private String password;
}
