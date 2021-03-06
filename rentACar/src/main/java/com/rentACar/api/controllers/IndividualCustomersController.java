package com.rentACar.api.controllers;

import java.rmi.RemoteException;
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

import com.rentACar.business.abstracts.IndividualCustomerService;
import com.rentACar.business.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.rentACar.business.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers/")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("add")
    public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException{
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}
	
	@DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
		return this.individualCustomerService.delete(id);
	}
	
	@PutMapping("update")
    public Result update(@RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
//	@GetMapping("getall")
//	public DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
//		return this.userService.getAll();
//	}
	
//	@GetMapping("getallbypage/{pageNo}/{pageSize}")
//	public DataResult<List<GetAllIndividualCustomerResponse>> getAll(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
//		return this.userService.getAll(pageNo, pageSize);
//	}
		

}
