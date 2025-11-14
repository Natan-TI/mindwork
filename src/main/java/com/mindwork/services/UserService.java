package com.mindwork.services;

import java.util.List;
import java.util.UUID;

import com.mindwork.dto.UserRegisterRequest;
import com.mindwork.dto.UserResponse;
import com.mindwork.dto.UserUpdateRequest;

public interface UserService {
	UserResponse register(UserRegisterRequest request);
	
	UserResponse getById(UUID id);
	
	List<UserResponse> listByOrganization(UUID organizationId);
	
	List<UserResponse> listAllUsers();
	
	UserResponse update(UUID id, UserUpdateRequest request);
	
	void delete(UUID id);
}
