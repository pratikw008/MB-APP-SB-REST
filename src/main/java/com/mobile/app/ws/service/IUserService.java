package com.mobile.app.ws.service;

import java.util.List;

import com.mobile.app.ws.dto.UserDto;

public interface IUserService {
	
	public UserDto saveUser(UserDto dto);
	
	public UserDto getUserById(String userId);
	
	public UserDto updateUser(String userId, UserDto userDto);
	
	public void deleteUser(String userId);
	
	List<UserDto> getAllUsers(int page, int size);
}
