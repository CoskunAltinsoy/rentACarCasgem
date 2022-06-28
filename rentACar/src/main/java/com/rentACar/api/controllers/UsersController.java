package com.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentACar.business.abstracts.UserService;
import com.rentACar.business.requests.users.CreateUserRequest;
import com.rentACar.business.requests.users.UpdateUserRequest;
import com.rentACar.business.responses.users.GetAllUsersResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/add")
    public Result add(@RequestBody CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException{
		return this.userService.add(createUserRequest);
	}
	
	@DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") int id) {
		return this.userService.delete(id);
	}
	
	@PutMapping("/update")
    public Result update(@RequestBody UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllUsersResponse>> getAll() {
		return this.userService.getAll();
	}
	
	@GetMapping("/getallbypage/{pageNo}/{pageSize}")
	public DataResult<List<GetAllUsersResponse>> getAll(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		return this.userService.getAll(pageNo, pageSize);
	}
		

}
