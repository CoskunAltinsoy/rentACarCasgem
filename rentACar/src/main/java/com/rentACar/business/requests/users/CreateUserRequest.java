package com.rentACar.business.requests.users;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

	//@Size(min = 2)
		private String firstName;
		
		//@Size(min = 2)
		private String lastName;
		
		//@Size(min = 11)
		private String tcNo;
		
		private String email;
		
		private LocalDate dateOfBirth;
		
		private String password;
}
