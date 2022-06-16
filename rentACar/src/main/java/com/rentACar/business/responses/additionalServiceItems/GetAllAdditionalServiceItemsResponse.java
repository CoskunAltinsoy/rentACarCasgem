package com.rentACar.business.responses.additionalServiceItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdditionalServiceItemsResponse {

	private int id;
	private String name;
	private double price;
}
