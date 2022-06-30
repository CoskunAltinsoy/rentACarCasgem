package com.rentACar.business.requests.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContactAddressRequestForCorporateCustomer {
	
	private int id;
	private String contactAddress;
	private int corporateCustomerId;
}
