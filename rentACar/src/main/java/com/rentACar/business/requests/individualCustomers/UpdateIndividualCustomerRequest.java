package com.rentACar.business.requests.individualCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIndividualCustomerRequest {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
