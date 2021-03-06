package com.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.InvoiceService;
import com.rentACar.business.requests.invoices.CreateInvoiceRequestForIndividualCustomer;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices/")
public class InvoicesController {
	
	private InvoiceService invoiceService;

	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}
	
	@PostMapping("addForIndividualCustomer") 
	public Result addForIndividualCustomer(CreateInvoiceRequestForIndividualCustomer createInvoiceRequestForIndividualCustomer) {
		return this.invoiceService.addForIndividualCustomer(createInvoiceRequestForIndividualCustomer);
	}

}
