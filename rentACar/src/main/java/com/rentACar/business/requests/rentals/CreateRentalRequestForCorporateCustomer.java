package com.rentACar.business.requests.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequestForCorporateCustomer {

	private LocalDate pickupDate;
	private LocalDate returnedDate;
	private int carId;
	private int pickupCityId;
	private int returnCityId;
	private int customerId;
}
