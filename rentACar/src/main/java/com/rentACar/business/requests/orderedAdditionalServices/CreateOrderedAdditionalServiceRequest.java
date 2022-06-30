package com.rentACar.business.requests.orderedAdditionalServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderedAdditionalServiceRequest {

	private int additionalServiceItemId;
	private int rentalId;
}
