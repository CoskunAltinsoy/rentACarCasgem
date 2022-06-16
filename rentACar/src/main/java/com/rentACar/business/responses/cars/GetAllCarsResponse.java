package com.rentACar.business.responses.cars;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {

	private int id;
	@Size(min = 3, max = 50)
	private String description;
	
	private double dailyPrice;
	private String plate;
	private int distance;
	private int brandId;
	private int colorId;
	private int cityId;
}
