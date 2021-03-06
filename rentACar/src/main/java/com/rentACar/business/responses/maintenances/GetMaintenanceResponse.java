package com.rentACar.business.responses.maintenances;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMaintenanceResponse {
	
	private int id;
	private LocalDate dateSent;
	private LocalDate dateReturn;
	private int carId;
}
