package com.rentACar.business.responses.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoiceRequestForIndividualCustomer {

	private int id;
	private String invoiceNumber;
	private int rentalId;
	private int additionalServiceId;
}
