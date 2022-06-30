package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface CorporateCustomerService {

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result delete(int id);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
//	DataResult<GetCorporateCustomerResponse> getById(int id);
//	DataResult<List<GetAllCorporateCustomersResponse>> getAll();
}
