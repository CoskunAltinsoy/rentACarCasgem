package com.rentACar.business.responses.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAddressesForIndividualCustomerResponse {

	private int id;
	private String contactAddress;
	private int individualCustomerId;
}