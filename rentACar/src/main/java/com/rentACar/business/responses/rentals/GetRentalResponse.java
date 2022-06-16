package com.rentACar.business.responses.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalResponse {

	private int id;
	private LocalDate pickupDate;
	private LocalDate returnedDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int pickupCityId;
	private int returnCityId;
}