package com.rentACar.api.controllers;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.CorporateCustomerService;
import com.rentACar.business.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers/")
public class CorporateCustomersController {

	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@PostMapping("add")
    public Result add(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) throws NumberFormatException, RemoteException{
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	
	@DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
		return this.corporateCustomerService.delete(id);
	}
	
	@PutMapping("update")
    public Result update(@RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	
}
