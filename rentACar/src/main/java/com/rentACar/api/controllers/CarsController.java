package com.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.CarService;
import com.rentACar.business.requests.cars.CreateCarRequest;
import com.rentACar.business.requests.cars.UpdateCarRequest;
import com.rentACar.business.responses.cars.GetAllCarsResponse;
import com.rentACar.business.responses.cars.GetCarResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars/")
public class CarsController {

	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		 return this.carService.add(createCarRequest);
		
	}
	
	@DeleteMapping("delete/{id}")
	public Result add(@PathVariable("id") int id) {
		return this.carService.delete(id);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<GetAllCarsResponse>> getAll() {
		return this.carService.getAll();
	}
	
	@GetMapping("getbyid/{id}")
	public DataResult<GetCarResponse> geyById(@PathVariable int id) {
		return this.carService.getById(id);
	}
	
}
