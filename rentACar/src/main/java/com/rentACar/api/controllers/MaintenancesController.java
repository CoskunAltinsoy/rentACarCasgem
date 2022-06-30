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

import com.rentACar.business.abstracts.MaintenanceService;
import com.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.rentACar.business.responses.maintenances.GetAllMaintenancesResponse;
import com.rentACar.business.responses.maintenances.GetMaintenanceResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/maintenances/")
public class MaintenancesController {

	private MaintenanceService maintenanceService;
	
	@Autowired
	public MaintenancesController(MaintenanceService maintenanceService) {
		super();
		this.maintenanceService = maintenanceService;
	}
	
	@PostMapping("add")
    public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		return this.maintenanceService.add(createMaintenanceRequest);
	}
	
	@DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
		return this.maintenanceService.delete(id);
	}
	
	@PutMapping("update")
    public Result update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceService.update(updateMaintenanceRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		return this.maintenanceService.getAll();
	}
	
	@GetMapping("getbyid/{id}")
	public DataResult<GetMaintenanceResponse> getById(@PathVariable int id) {
		return this.maintenanceService.getById(id);
	}
	
}
