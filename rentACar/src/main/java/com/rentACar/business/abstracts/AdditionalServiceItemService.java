package com.rentACar.business.abstracts;

import com.rentACar.business.requests.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.rentACar.business.requests.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.rentACar.core.utilities.results.Result;

public interface AdditionalServiceItemService {

	Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest);
	Result delete(int id);
	Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest);
}
