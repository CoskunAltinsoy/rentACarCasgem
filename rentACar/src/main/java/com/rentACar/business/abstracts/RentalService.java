package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.rentals.CreateRentalRequest;
import com.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.rentACar.business.responses.rentals.GetRentalResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.entities.concretes.Rental;

public interface RentalService {

	Result add(CreateRentalRequest createRentalRequest);
	Result delete(int id);
	Result update(UpdateRentalRequest updateRentalRequest);
	DataResult<List<GetAllRentalsResponse>> getAll();
	DataResult<GetRentalResponse> getById(int id);
}
