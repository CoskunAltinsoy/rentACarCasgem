package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.rentals.CreateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.CreateRentalRequestForIndividualCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForIndividualCustomer;
import com.rentACar.business.responses.rentals.GetAllRentalsResponseForIndividualCustomer;
import com.rentACar.business.responses.rentals.GetRentalResponseForIndividualCustomer;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.entities.concretes.Rental;

public interface RentalService {

	Result addForIndividualCustomer(CreateRentalRequestForIndividualCustomer createRentalRequestForIndividualCustomer);
	Result addForCorporateCustomer(CreateRentalRequestForCorporateCustomer createRentalRequestForCorporateCustomer);
	Result delete(int id);
	Result updateForIndividualCustomer(UpdateRentalRequestForIndividualCustomer updateRentalRequestForIndividualCustomer);
	Result updateForCorporateCustomer(UpdateRentalRequestForCorporateCustomer updateRentalRequestForCorporateCustomer);
//	DataResult<List<GetAllIndividualCustomerRentalResponse>> getAll();
//	DataResult<GetIndividualCustomerRentalResponse> getById(int id);
}
