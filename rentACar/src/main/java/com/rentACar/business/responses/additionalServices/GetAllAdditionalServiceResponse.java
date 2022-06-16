package com.rentACar.business.responses.additionalServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdditionalServiceResponse {

	private int id;
	private int additionalServiceItemId;
	private int rentalId;
}
