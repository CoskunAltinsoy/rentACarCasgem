package com.rentACar.business.abstracts;

import com.rentACar.business.requests.orderedAdditionalServices.CreateOrderedAdditionalServiceRequest;
import com.rentACar.core.utilities.results.Result;

public interface OrderedAdditionalServiceService {

	Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest);
	Result delete(int id);
}
