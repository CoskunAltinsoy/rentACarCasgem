package com.rentACar.business.responses.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCorporateCustomerResponse {

	private int id;
	private String companyName;
	private String taxNumber;
}
