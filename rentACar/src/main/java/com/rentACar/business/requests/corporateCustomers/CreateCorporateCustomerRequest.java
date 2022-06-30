package com.rentACar.business.requests.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRequest {
	
	private String companyName;
	private String taxNumber;
}
