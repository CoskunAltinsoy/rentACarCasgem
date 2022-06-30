package com.rentACar.business.requests.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceRequestForCorporateCustomer {

	private int id;
	private String invoiceNumber;
	private int rentalId;
	private int additionalServiceId;
}
