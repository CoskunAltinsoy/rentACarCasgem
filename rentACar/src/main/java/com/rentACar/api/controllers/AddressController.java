package com.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.AddressService;
import com.rentACar.business.requests.addresses.CreateAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.CreateAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateBillingAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateBillingAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateContactAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateContactAddressRequestForIndividualCustomer;
import com.rentACar.core.utilities.results.Result;

import lombok.Delegate;

@RestController
@RequestMapping("/api/addresses/")
public class AddressController {
	
	private AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}


	@PostMapping("addForIndividualCustomer")
	public Result addForIndividualCustomer(@RequestBody CreateAddressRequestForIndividualCustomer createAddressRequestForIndividualCustomer) {
		return this.addressService.addForIndividualCustomer(createAddressRequestForIndividualCustomer);
	}
	
	@PostMapping("addForCorporateCustomer")
	public Result addForCorporateCustomer(@RequestBody CreateAddressRequestForCorporateCustomer createAddressRequestForCorporateCustomer) {
		return this.addressService.addForCorporateCustomer(createAddressRequestForCorporateCustomer);
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable("id") int id) {
		return this.addressService.delete(id);
	}
	
	@PutMapping("updateContactAddressForIndivudualCustomer")
	public Result updateContactAddressForIndivudualCustomer(@RequestBody UpdateContactAddressRequestForIndividualCustomer updateContactAddressRequestForIndividualCustomer) {
		return this.addressService.updateContactAddressForIndivudualCustomer(updateContactAddressRequestForIndividualCustomer);
	}
	
	@PutMapping("updateContactAddressForCorporateCustomer")
	public Result updateContactAddressForCorporateCustomer(@RequestBody UpdateContactAddressRequestForCorporateCustomer updateContactAddressRequestForCorporateCustomer) {
		return this.addressService.updateContactAddressForCorporateCustomer(updateContactAddressRequestForCorporateCustomer);
	}
	
	@PutMapping("updateBillingAddressForCorporateCustomer")
	public Result updateBillingAddressForCorporateCustomer(@RequestBody UpdateBillingAddressRequestForCorporateCustomer updateBillingAddressRequestForCorporateCustomer) {
		return this.addressService.updateBillingAddressForCorporateCustomer(updateBillingAddressRequestForCorporateCustomer);
	}
	
	@PutMapping("updateBillingAddressForIndividualCustomer")
	public Result updateBillingAddressForIndividualCustomer(@RequestBody UpdateBillingAddressRequestForIndividualCustomer updateBillingAddressRequestForIndividualCustomer) {
		return this.addressService.updateBillingAddressForIndividualCustomer(updateBillingAddressRequestForIndividualCustomer);
	}
	
	@PutMapping("updateForIndividualCustomer")
	public Result updateForIndividualCustomer(@RequestBody UpdateAddressRequestForIndividualCustomer updateAddressRequestForIndividualCustomer) {
		return this.addressService.updateForIndividualCustomer(updateAddressRequestForIndividualCustomer);
	}
	
	@PutMapping("updateForCorporateCustomer")
	public Result updateForCorporateCustomer(@RequestBody UpdateAddressRequestForCorporateCustomer updateAddressRequestForCorporateCustomer) {
		return this.addressService.updateForCorporateCustomer(updateAddressRequestForCorporateCustomer);
	}
	
	
}
