package com.mobile.app.ws.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.entity.UserEntity;
import com.mobile.app.ws.exceptions.InvalidUserIdException;
import com.mobile.app.ws.mapper.UserMapper;
import com.mobile.app.ws.model.response.ErrorMessages;
import com.mobile.app.ws.repository.UserRepository;
import com.mobile.app.ws.service.IUserService;
import com.mobile.app.ws.util.Utils;

@Service
public class UserServiceImpl implements IUserService {

	private UserRepository userRepository;

	private UserMapper userMapper;

	private Utils utils;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, Utils utils,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.utils = utils;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto saveUser(UserDto dto) {
		if(userRepository.existsByEmail(dto.getEmail()))
			throw new RuntimeException("Record Already Exists");

		UserEntity userEntity = userMapper.convertUserDtoToUserEntity(dto);
		userEntity.setUserId(utils.generateUserId(30));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

		UserEntity savedInDb = userRepository.save(userEntity);
		return userMapper.convertUserEntityToUserDto(savedInDb);
	}

	@Override
	public UserDto getUserById(String userId) {
		return userRepository.findByUserId(userId)
				.map(userMapper::convertUserEntityToUserDto)
				.orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		return userRepository.findByUserId(userId)
			   .map(userEntity -> this.updateUser(userEntity, userDto))
			   .orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage())); 
	}

	private UserDto updateUser(UserEntity userEntity, UserDto userDto) {
		if(userDto.getFirstName() != null || !userDto.getFirstName().isEmpty()) 
			userEntity.setFirstName(userDto.getFirstName());
		
		if(userDto.getLastName() != null || !userDto.getLastName().isEmpty())
			userEntity.setLastName(userDto.getLastName());
		
		if(userDto.getEmail() != null || !userDto.getEmail().isEmpty())
			userEntity.setEmail(userDto.getEmail());
		
		if(userDto.getPassword() != null || !userDto.getPassword().isEmpty())
			userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		UserEntity savedInDb = userRepository.save(userEntity);
		return userMapper.convertUserEntityToUserDto(savedInDb);
	}
}
