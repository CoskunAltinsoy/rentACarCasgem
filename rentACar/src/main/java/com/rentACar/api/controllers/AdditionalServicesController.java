package com.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.rentACar.business.requests.orderedAdditionalServices.CreateOrderedAdditionalServiceRequest;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalServices/")
public class AdditionalServicesController {

	private OrderedAdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(OrderedAdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateOrderedAdditionalServiceRequest createAdditionalServiceRequest) {
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable("id") int id) {
		return this.additionalServiceService.delete(id);
	}
	
	/*@PutMapping("/update")
	public Result update(@RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}*/
}
