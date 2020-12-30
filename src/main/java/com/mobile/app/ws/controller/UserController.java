package com.mobile.app.ws.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.mapper.UserMapper;
import com.mobile.app.ws.mapper.UserRequestMapper;
import com.mobile.app.ws.model.request.UserRequest;
import com.mobile.app.ws.model.response.OpeartionName;
import com.mobile.app.ws.model.response.OpeartionStatus;
import com.mobile.app.ws.model.response.OperationStatusModel;
import com.mobile.app.ws.model.response.UserResponse;
import com.mobile.app.ws.service.IUserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	private IUserService userService;
	
	private UserMapper userMapper;
	
	private UserRequestMapper userRequestMapper;

	public UserController(IUserService userService, UserMapper userMapper, UserRequestMapper userRequestMapper) {
		super();
		this.userService = userService;
		this.userMapper = userMapper;
		this.userRequestMapper = userRequestMapper;
	}

	@PostMapping(consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				},
				produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				}
	)
	public UserResponse saveUser(@RequestBody UserRequest userDetails) {
		UserDto userDto = userRequestMapper.mapUserRequestToUserDto(userDetails);
		UserDto savedUser = userService.saveUser(userDto);
		UserResponse userResponse = userMapper.mapUserDtoToUserResponse(savedUser);
		return userResponse; 
	}
	
	@GetMapping(path = "/{userId}",
				produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				}
	)
	public UserResponse getUserByUserId(@PathVariable("userId") String userId) {
		UserDto userDto = userService.getUserById(userId);
		return userMapper.mapUserDtoToUserResponse(userDto);
	}

	@PutMapping(path = "/{userId}",
				consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				},
				produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
				}
	)
	public UserResponse updateUser(@PathVariable("userId")String userId, 
										  @RequestBody UserRequest userRequest) {
		
		UserDto userDto = userRequestMapper.mapUserRequestToUserDto(userRequest);
		UserDto updatedUser = userService.updateUser(userId, userDto);
		return userMapper.mapUserDtoToUserResponse(updatedUser);
	}

	@DeleteMapping(path = "/{userId}",
				   produces = {
					   MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public OperationStatusModel deleteUser(@PathVariable("userId") String userId) {
		userService.deleteUser(userId);
		return new OperationStatusModel(OpeartionName.DELETE.name(),OpeartionStatus.SUCCESS.name());
	}
	
	@GetMapping(produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public List<UserResponse> getAllUser(@RequestParam(name = "page", defaultValue = "0")int page, 
			   @RequestParam(name = "size", defaultValue = "10")int size) {
		List<UserDto> dtos = userService.getAllUsers(page, size);
		return userMapper.mapListUserDtoToListUserResponse(dtos);
	}
}
