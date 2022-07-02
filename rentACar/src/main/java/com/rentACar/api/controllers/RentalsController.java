package com.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.concretes.RentalManager;
import com.rentACar.business.requests.rentals.CreateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForCorporateCustomer;
import com.rentACar.business.responses.rentals.GetAllRentalsResponseForIndividualCustomer;
import com.rentACar.business.responses.rentals.GetRentalResponseForIndividualCustomer;
import com.rentACar.core.utilities.results.DataResult;
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
	
	@PostMapping("add")
	public Result add(@RequestBody CreateRentalRequestForCorporateCustomer createRentalRequest) {
		return this.rentalManager.add(createRentalRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result add(@PathVariable("id") int id) {
		return this.rentalManager.delete(id);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody UpdateRentalRequestForCorporateCustomer updateRentalRequest) {
		return this.rentalManager.update(updateRentalRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<GetAllRentalsResponseForIndividualCustomer>> getAll(){
		return this.rentalManager.getAll();
	}
	
	@GetMapping("getById/{id}")
	public DataResult<GetRentalResponseForIndividualCustomer> getById(@PathVariable("id") int id){
		return this.rentalManager.getById(id);
	}
	

}