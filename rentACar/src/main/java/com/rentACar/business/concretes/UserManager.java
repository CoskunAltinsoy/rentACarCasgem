package com.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.UserService;
import com.rentACar.business.requests.users.CreateUserRequest;
import com.rentACar.business.requests.users.UpdateUserRequest;
import com.rentACar.business.responses.users.GetAllUsersResponse;
import com.rentACar.business.responses.users.GetUserResponse;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.UserRepository;
import com.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	
	
	@Autowired
	public UserManager(UserRepository userRepository, ModelMapperService modelMapperService
			) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateUserRequest createUserRequest)  {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.userRepository.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER.UPDATED");
	}

	@Override
	public DataResult<List<GetAllUsersResponse>> getAll() {
		List<User> users = this.userRepository.findAll();
		List<GetAllUsersResponse> response =
				users.stream().map(user -> this.modelMapperService.forResponse()
						.map(user,GetAllUsersResponse.class)).collect(Collectors.toList());
		    return new SuccessDataResult<List<GetAllUsersResponse>>(response);
	}

	@Override
	public DataResult<User> getById(GetUserResponse getUserResponse) {
		// TODO Auto-generated method stub
		return null;
	}

}
