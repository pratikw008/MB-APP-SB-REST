package com.mobile.app.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.mapper.UserMapper;
import com.mobile.app.ws.model.request.UserDetailsRequest;
import com.mobile.app.ws.model.response.UserDetailsResponse;
import com.mobile.app.ws.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private IUserService userService;
	
	private UserMapper userMapper;
	
	public UserController(IUserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@PostMapping(consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public UserDetailsResponse saveUser(@RequestBody UserDetailsRequest userDetails) {
		UserDto userDto = userMapper.convertUserDetailsRequestToUserDto(userDetails);
		UserDto savedUser = userService.saveUser(userDto);
		UserDetailsResponse userDetailsResponse = userMapper.convertUserDtoToUserDetailsResponse(savedUser);
		return userDetailsResponse; 
	}
	
	@GetMapping
	public String getUser() {
		return "Get User Called";
	}

	@PutMapping
	public String updateUser() {
		return "Update User Called"; 
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete User Called"; 
	}
}
