package com.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.concretes.RentalManager;
import com.rentACar.business.requests.rentals.CreateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.CreateRentalRequestForIndividualCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForIndividualCustomer;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals/")
public class RentalsController {
	
	private RentalManager rentalManager;

	@Autowired
	public RentalsController(RentalManager rentalManager) {
		super();
		this.rentalManager = rentalManager;
	}
	
	@PostMapping("addForIndividualCustomer")
	public Result addForIndividualCustomer(@RequestBody CreateRentalRequestForIndividualCustomer createRentalRequestForIndividualCustomer) {
		return this.rentalManager.addForIndividualCustomer(createRentalRequestForIndividualCustomer);
	}
	
	@PostMapping("addForCorporateCustomer")
	public Result addForCorporateCustomer(@RequestBody CreateRentalRequestForCorporateCustomer createRentalRequestForCorporateCustomer) {
		return this.rentalManager.addForCorporateCustomer(createRentalRequestForCorporateCustomer);
	}
	
	@DeleteMapping("delete/{id}")
	public Result add(@PathVariable("id") int id) {
		return this.rentalManager.delete(id);
	}
	
	@PutMapping("updateForIndividualCustomer")
	public Result updateForIndividualCustomer(@RequestBody UpdateRentalRequestForIndividualCustomer updateRentalRequestForIndividualCustomer) {
		return this.rentalManager.updateForIndividualCustomer(updateRentalRequestForIndividualCustomer);
	}
	
	@PutMapping("updateForIndividualCustomer")
	public Result updateForCorporateCustomer(@RequestBody UpdateRentalRequestForCorporateCustomer updateRentalRequestForCorporateCustomer) {
		return this.rentalManager.updateForCorporateCustomer(updateRentalRequestForCorporateCustomer);
	}
	
//	@GetMapping("getAll")
//	public DataResult<List<GetAllRentalsResponseForIndividualCustomer>> getAll(){
//		return this.rentalManager.getAll();
//	}
//	
//	@GetMapping("getById/{id}")
//	public DataResult<GetRentalResponseForIndividualCustomer> getById(@PathVariable("id") int id){
//		return this.rentalManager.getById(id);
//	}
	

}