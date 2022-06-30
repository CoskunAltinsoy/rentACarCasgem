package com.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.CorporateCustomerService;
import com.rentACar.business.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{

	private CorporateCustomerRepository corporateCustomerRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerRepository corporateCustomerRepository,
			ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerRepository = corporateCustomerRepository;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.corporateCustomerRepository.deleteById(id);
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

}
