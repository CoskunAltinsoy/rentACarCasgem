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

import com.rentACar.business.abstracts.ColorService;
import com.rentACar.business.requests.colors.CreateColorRequest;
import com.rentACar.business.requests.colors.UpdateColorRequest;
import com.rentACar.business.responses.colors.GetAllColorsResponse;
import com.rentACar.business.responses.colors.GetColorResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id") int id ) {
		return this.colorService.delete(id);
	}
	
	@PutMapping("/update")
	public Result delete(@RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllColorsResponse>> getAll() {
		return this.colorService.getAll();
	}
	
	@GetMapping("/getbyid/{id}")
	public DataResult<GetColorResponse> geyById(@PathVariable("id") int id) {
		return this.colorService.getById(id);
	}

}
