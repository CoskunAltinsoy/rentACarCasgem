package com.rentACar.business.abstracts;

import com.rentACar.business.requests.invoices.CreateInvoiceRequestForIndividualCustomer;
import com.rentACar.core.utilities.results.Result;

public interface InvoiceService {

	Result addForIndividualCustomer(CreateInvoiceRequestForIndividualCustomer createInvoiceRequestForIndividualCustomer);
}
