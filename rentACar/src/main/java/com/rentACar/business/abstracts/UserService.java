package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.users.CreateUserRequest;
import com.rentACar.business.requests.users.UpdateUserRequest;
import com.rentACar.business.responses.users.GetAllUsersResponse;
import com.rentACar.business.responses.users.GetUserResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.entities.concretes.User;

public interface UserService {

	Result add(CreateUserRequest createUserRequest);
	Result delete(int id);
	Result update(UpdateUserRequest updateUserRequest);
	DataResult<List<GetAllUsersResponse>> getAll();
	DataResult<User> getById(GetUserResponse getUserResponse);
}
