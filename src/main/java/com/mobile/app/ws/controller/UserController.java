package com.mobile.app.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(path = "/users")
public class UserController {
	
	private IUserService userService;
	
	private UserMapper userMapper;
	
	public UserController(IUserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@PostMapping(consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				},
				produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				}
	)
	public UserDetailsResponse saveUser(@RequestBody UserDetailsRequest userDetails) {
		UserDto userDto = userMapper.convertUserDetailsRequestToUserDto(userDetails);
		UserDto savedUser = userService.saveUser(userDto);
		UserDetailsResponse userDetailsResponse = userMapper.convertUserDtoToUserDetailsResponse(savedUser);
		return userDetailsResponse; 
	}
	
	@GetMapping(path = "/{userId}",
				produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				}
	)
	public UserDetailsResponse getUserByUserId(@PathVariable("userId") String userId) {
		UserDto userDto = userService.getUserById(userId);
		return userMapper.convertUserDtoToUserDetailsResponse(userDto);
	}

	@PutMapping(path = "/{userId}",
				consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				},
				produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				}
	)
	public UserDetailsResponse updateUser(@PathVariable("userId")String userId, 
										  @RequestBody UserDetailsRequest userDetailsRequest) {
		
		UserDto userDto = userMapper.convertUserDetailsRequestToUserDto(userDetailsRequest);
		UserDto updatedUser = userService.updateUser(userId, userDto);
		return userMapper.convertUserDtoToUserDetailsResponse(updatedUser);
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete User Called"; 
	}
}
