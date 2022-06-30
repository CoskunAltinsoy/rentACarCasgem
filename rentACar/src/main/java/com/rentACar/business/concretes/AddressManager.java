package com.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.AddressService;
import com.rentACar.business.requests.addresses.CreateAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.CreateAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateBillingAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateBillingAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateContactAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateContactAddressRequestForIndividualCustomer;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.AddressRepository;
import com.rentACar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService{

	private AddressRepository addressRepository;
	private ModelMapperService modelMapperService;
	@Autowired
	public AddressManager(AddressRepository addressRepository, ModelMapperService modelMapperService) {
		super();
		this.addressRepository = addressRepository;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result addForIndividualCustomer(CreateAddressRequestForIndividualCustomer createAddressRequestForIndividualCustomer) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequestForIndividualCustomer, Address.class);
		if (address.getBillingAddress() == null || address.getBillingAddress() =="") {
			address.setBillingAddress(createAddressRequestForIndividualCustomer.getContactAddress());
		}		
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}
	

	@Override
	public Result addForCorporateCustomer(CreateAddressRequestForCorporateCustomer createAddressRequestForCorporateCustomer) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequestForCorporateCustomer, Address.class);
		if (address.getBillingAddress() == null || address.getBillingAddress() =="") {
			address.setBillingAddress(createAddressRequestForCorporateCustomer.getContactAddress());
		}		
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.addressRepository.deleteById(null);
		return new SuccessResult("ADDRESS.DELETED");
	}

	@Override
	public Result updateContactAddressForIndivudualCustomer(UpdateContactAddressRequestForIndividualCustomer updateContactAddressRequestForIndividualCustomer) {
		Address address = this.addressRepository.findById(updateContactAddressRequestForIndividualCustomer.getId());
		address.setContactAddress(updateContactAddressRequestForIndividualCustomer.getContactAddress());
		this.addressRepository.save(address);
		return new SuccessResult("CONTACTADDRESS.UPDATED");
	}
	
	@Override
	public Result updateContactAddressForCorporateCustomer(UpdateContactAddressRequestForCorporateCustomer updateContactAddressRequestForCorporateCustomer) {
		Address address = this.addressRepository.findById(updateContactAddressRequestForCorporateCustomer.getId());
		address.setContactAddress(updateContactAddressRequestForCorporateCustomer.getContactAddress());
		this.addressRepository.save(address);
		return new SuccessResult("CONTACTADDRESS.UPDATED");
	}

	@Override
	public Result updateBillingAddressForIndividualCustomer(UpdateBillingAddressRequestForIndividualCustomer updateBillingAddressRequestForIndividualCustomer) {
		Address address = this.addressRepository.findById(updateBillingAddressRequestForIndividualCustomer.getId());
		address.setBillingAddress(updateBillingAddressRequestForIndividualCustomer.getBillingAddress());
		this.addressRepository.save(address);
		return new SuccessResult("BILLINGADDRESS.UPDATED");
	}
	
	@Override
	public Result updateBillingAddressForCorporateCustomer(UpdateBillingAddressRequestForCorporateCustomer updateBillingAddressRequestForCorporateCustomer) {
		Address address = this.addressRepository.findById(updateBillingAddressRequestForCorporateCustomer.getId());
		address.setBillingAddress(updateBillingAddressRequestForCorporateCustomer.getBillingAddress());
		this.addressRepository.save(address);
		return new SuccessResult("BILLINGADDRESS.UPDATED");
	}

	@Override
	public Result updateForIndividualCustomer(UpdateAddressRequestForIndividualCustomer updateAddressRequestForIndividualCustomer) {
		Address address = this.addressRepository.findById(updateAddressRequestForIndividualCustomer.getId());
		address.setBillingAddress(updateAddressRequestForIndividualCustomer.getContactAddress());
		this.addressRepository.save(address);
		return new SuccessResult("UPDATED");
	}
	
	@Override
	public Result updateForCorporateCustomer(UpdateAddressRequestForCorporateCustomer updateAddressRequestForCorporateCustomer) {
		Address address = this.addressRepository.findById(updateAddressRequestForCorporateCustomer.getId());
		address.setBillingAddress(updateAddressRequestForCorporateCustomer.getContactAddress());
		this.addressRepository.save(address);
		return new SuccessResult("UPDATED");
	}

}
