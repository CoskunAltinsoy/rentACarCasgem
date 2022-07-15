package com.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.IndividualCustomerService;
import com.rentACar.business.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.rentACar.business.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.rentACar.business.responses.individualCustomers.GetAllIndividualCustomersResponse;
import com.rentACar.business.responses.individualCustomers.GetIndividualCustomerResponse;
import com.rentACar.core.utilities.adapters.abstracts.UserCheckService;
import com.rentACar.core.utilities.exceptions.BusinessException;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.rentACar.entities.concretes.IndividualCustomer;
import com.rentACar.entities.concretes.User;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{

	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	private UserCheckService userCheckService;
	
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository, ModelMapperService modelMapperService,
			UserCheckService userCheckService) {
		super();
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.userCheckService = userCheckService;
	}
	
	@Override
	public Result add(CreateIndividualCustomerRequest  createIndividualCustomerRequest) throws NumberFormatException, RemoteException  {
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		checkFromMernis(Long.parseLong(createIndividualCustomerRequest.getNationalIdentity()), createIndividualCustomerRequest.getFirstName(),
				createIndividualCustomerRequest.getLastName(), createIndividualCustomerRequest.getDateOfBirth().getYear());
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.individualCustomerRepository.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.UPDATED");
	}

//	@Override
//	public DataResult<List<GetAllUsersResponse>> getAll() {
//		List<User> users = this.individualCustomerRepository.findAll();
//		List<GetAllUsersResponse> response =
//				users.stream().map(user -> this.modelMapperService.forResponse()
//						.map(user,GetAllUsersResponse.class)).collect(Collectors.toList());
//		    return new SuccessDataResult<List<GetAllUsersResponse>>(response);
//	}
//
//	@Override
//	public DataResult<User> getById(GetUserResponse getUserResponse) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public DataResult<List<GetAllUsersResponse>> getAll(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
//		List<User> users = this.userRepository.findAll(pageable).getContent();
//		List<GetAllUsersResponse> response =
//				users.stream().map(user -> this.modelMapperService.forResponse()
//						.map(user,GetAllUsersResponse.class)).collect(Collectors.toList());
//		return new SuccessDataResult<List<GetAllUsersResponse>>(response);
//	}
	
	private void checkFromMernis(Long tcNo, String firstName, String lastName, int dateOfBirth) throws RemoteException {
		if (!(this.userCheckService.checkIfUserRealPerson(tcNo, firstName, lastName, dateOfBirth))) {
			throw new BusinessException("VALIDATION.INCORRECT");
		}
	}
}
