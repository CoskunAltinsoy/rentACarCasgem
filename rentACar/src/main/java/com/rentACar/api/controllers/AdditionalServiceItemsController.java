package com.rentACar.api.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.AdditionalServiceItemService;
import com.rentACar.business.requests.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.rentACar.business.requests.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalServiceItems")
public class AdditionalServiceItemsController {
	
	private AdditionalServiceItemService additionalServiceItemService;

	public AdditionalServiceItemsController(AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.additionalServiceItemService = additionalServiceItemService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalServiceItemRequest additionalServiceItemRequest) {
		return this.additionalServiceItemService.add(additionalServiceItemRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id") int id) {
		return this.additionalServiceItemService.delete(id);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		return this.additionalServiceItemService.update(updateAdditionalServiceItemRequest);
	}

}
