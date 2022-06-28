package com.rentACar.business.responses.users;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

	private int id;
	private String firstName;
	private String lastName;		
	private String nationalIdentity;
	private String email;		
	private LocalDate dateOfBirth;	
	private String password;
}
