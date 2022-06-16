package com.rentACar.business.abstracts;

import com.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {

	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result delete(int id);
}
