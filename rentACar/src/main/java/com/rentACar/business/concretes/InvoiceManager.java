package com.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentACar.business.abstracts.InvoiceService;
import com.rentACar.business.requests.invoices.CreateInvoiceRequestForIndividualCustomer;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.rentACar.dataAccess.abstracts.OrderedAdditionalServiceRepository;
import com.rentACar.dataAccess.abstracts.RentalRepository;
import com.rentACar.entities.concretes.Invoice;
import com.rentACar.entities.concretes.OrderedAdditionalService;
import com.rentACar.entities.concretes.Rental;

public class InvoiceManager implements InvoiceService{

	private InvoiceRepository invoiceRepository;
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private OrderedAdditionalServiceRepository orderedAdditionalServiceRepository;
	
	
	@Autowired
	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService,
			RentalRepository rentalRepository, OrderedAdditionalServiceRepository orderedAdditionalServiceRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
		this.orderedAdditionalServiceRepository = orderedAdditionalServiceRepository;
	}
	
	
	@Override
	public Result addForIndividualCustomer(
			CreateInvoiceRequestForIndividualCustomer createInvoiceRequestForIndividualCustomer) {
		Rental rental = this.rentalRepository.findById(createInvoiceRequestForIndividualCustomer.getRentalId());
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequestForIndividualCustomer, Invoice.class);
		double totalPrice = rental.getTotalPrice() ;
		for (OrderedAdditionalService item : rental.getAdditionalServices()) {
			totalPrice += item.getTotalPrice();
		}
		invoice.setTotalPrice(totalPrice);
	    this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.ADDED");
	}

}
