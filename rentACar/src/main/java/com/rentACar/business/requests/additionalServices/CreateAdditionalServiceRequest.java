package com.rentACar.business.requests.additionalServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceRequest {

	private int additionalServiceItemId;
	private int rentalId;
}