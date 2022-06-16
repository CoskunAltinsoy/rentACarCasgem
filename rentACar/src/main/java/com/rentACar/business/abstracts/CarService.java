package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.cars.CreateCarRequest;
import com.rentACar.business.requests.cars.UpdateCarRequest;
import com.rentACar.business.responses.cars.GetAllCarsResponse;
import com.rentACar.business.responses.cars.GetCarResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.entities.concretes.Car;

public interface CarService {

	Result add(CreateCarRequest createCarRequest);
	Result delete(int id);
	Result update(UpdateCarRequest updateCarRequest);
	DataResult<List<GetAllCarsResponse>> getAll();
	DataResult<GetCarResponse> getById(int id);
}
