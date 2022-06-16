package com.rentACar.business.responses.cars;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {

	private int id;
	private String description;
	private double dailyPrice;
	private String plate;
	private int distance;
	private int brandId;
	private int colorId;
	private int cityId;
}
