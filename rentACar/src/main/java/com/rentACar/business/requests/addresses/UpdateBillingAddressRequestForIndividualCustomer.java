package com.rentACar.business.requests.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBillingAddressRequestForIndividualCustomer {

	private int id;
	private String billingAddress;
	private int individualCustomerId;
}
