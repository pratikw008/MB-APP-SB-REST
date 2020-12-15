package com.mobile.app.ws.service.impl;

import org.springframework.stereotype.Service;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.entity.UserEntity;
import com.mobile.app.ws.mapper.UserMapper;
import com.mobile.app.ws.repository.UserRepository;
import com.mobile.app.ws.service.IUserService;
import com.mobile.app.ws.util.Utils;

@Service
public class UserServiceImpl implements IUserService {
	
	private UserRepository userRepository;
	
	private UserMapper userMapper;

	private Utils utils;
	

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, Utils utils) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.utils = utils;
	}


	@Override
	public UserDto saveUser(UserDto dto) {
		if(userRepository.existsByEmail(dto.getEmail()))
			throw new RuntimeException("Record Already Exists");
		
		UserEntity userEntity = userMapper.convertUserDtoToUserEntity(dto);
		userEntity.setUserId(utils.generateUserId(30));
		UserEntity savedInDb = userRepository.save(userEntity);
		return userMapper.convertUserEntityToUserDto(savedInDb);
	}

}
