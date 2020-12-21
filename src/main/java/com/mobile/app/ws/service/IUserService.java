package com.mobile.app.ws.service;

import com.mobile.app.ws.dto.UserDto;

public interface IUserService {
	
	public UserDto saveUser(UserDto dto);
	
	public UserDto getUserById(String userId);
	
	public UserDto updateUser(String userId, UserDto userDto);
}
