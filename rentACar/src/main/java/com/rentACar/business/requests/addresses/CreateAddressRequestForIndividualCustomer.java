package com.rentACar.business.requests.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequestForIndividualCustomer {

	private String contactAddress;
	private String billingAddress;
	private int individualCustomerId;
}
