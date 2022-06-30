package com.rentACar.business.abstracts;

import com.rentACar.business.requests.addresses.CreateAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.CreateAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateBillingAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateBillingAddressRequestForIndividualCustomer;
import com.rentACar.business.requests.addresses.UpdateContactAddressRequestForCorporateCustomer;
import com.rentACar.business.requests.addresses.UpdateContactAddressRequestForIndividualCustomer;
import com.rentACar.core.utilities.results.Result;

public interface AddressService {

	Result addForIndividualCustomer(CreateAddressRequestForIndividualCustomer createAddressRequestForIndividualCustomer);
	Result addForCorporateCustomer(CreateAddressRequestForCorporateCustomer createAddressRequestForCorporateCustomer);
	Result delete(int id);
	Result updateContactAddressForIndivudualCustomer(UpdateContactAddressRequestForIndividualCustomer updateContactAddressRequestForIndividualCustomer);
	Result updateContactAddressForCorporateCustomer(UpdateContactAddressRequestForCorporateCustomer updateContactAddressRequestForCorporateCustomer);
	Result updateBillingAddressForIndividualCustomer(UpdateBillingAddressRequestForIndividualCustomer updateBillingAddressRequestForIndividualCustomer);
	Result updateBillingAddressForCorporateCustomer(UpdateBillingAddressRequestForCorporateCustomer updateBillingAddressRequestForCorporateCustomer);
	Result updateForIndividualCustomer(UpdateAddressRequestForIndividualCustomer updateAddressRequestForIndividualCustomer);
	Result updateForCorporateCustomer(UpdateAddressRequestForCorporateCustomer updateAddressRequestForCorporateCustomer);
//	DataResult<List<Getall>> getAll();
//	DataResult<GetByIdAddressResponse> getById(int id);
}