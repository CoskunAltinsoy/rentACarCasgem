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

import com.rentACar.business.abstracts.BrandService;
import com.rentACar.business.requests.brands.CreateBrandRequest;
import com.rentACar.business.requests.brands.UpdateBrandRequest;
import com.rentACar.business.responses.brands.GetAllBrandsResponse;
import com.rentACar.business.responses.brands.GetBrandResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/sayhello")//endpoint
	public String sayHello() {
		return "Hello Spring";
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);		
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id") int id) {
		return this.brandService.delete(id);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);	
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		return this.brandService.getAll();
		
	}
	
	@GetMapping("/getbyid/{id}")
	public DataResult<GetBrandResponse> geyById(@PathVariable int id) {
		return this.brandService.getById(id);
	}
}
