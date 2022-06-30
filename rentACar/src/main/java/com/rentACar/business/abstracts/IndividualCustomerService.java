package com.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.rentACar.business.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.rentACar.business.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.rentACar.business.responses.users.GetAllUsersResponse;
import com.rentACar.business.responses.users.GetUserResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.entities.concretes.User;

public interface IndividualCustomerService {

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException;
	Result delete(int id);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
//	DataResult<List<GetAllIndividualCustomerResponse>> getAll();
//	DataResult<List<GetAllIndividualCustomerResponse>> getAll(int pageNo, int pageSize);
//	DataResult<GetIndividualCustomerResponse> getById(int id);
}
