package com.rentACar.business.requests.additionalServiceItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalServiceItemRequest {
	private int id;
	private String name;
	private double price;
}
