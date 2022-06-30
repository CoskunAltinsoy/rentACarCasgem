package com.rentACar.business.requests.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequestForCorporateCustomer {

	private String contactAddress;
	private String billingAddress;
	private int corporateCustomerId;
}
