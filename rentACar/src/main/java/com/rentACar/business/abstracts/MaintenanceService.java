package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.rentACar.business.responses.maintenances.GetAllMaintenancesResponse;
import com.rentACar.business.responses.maintenances.GetMaintenanceResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.entities.concretes.Maintenance;

public interface MaintenanceService {

	Result add(CreateMaintenanceRequest createMaintenanceRequest);
	Result delete(int id);
	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);
	DataResult<List<GetAllMaintenancesResponse>> getAll();
	DataResult<GetMaintenanceResponse> getById(int id);
}
