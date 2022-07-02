package com.rentACar.business.responses.orderedAdditionalServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdditionalServiceResponse {

	private int id;
	private int totalDay;
	private int totalPrice;
	private int additionalServiceItemId;
	private int rentalId;
}
