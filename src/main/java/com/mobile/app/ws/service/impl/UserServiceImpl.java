package com.mobile.app.ws.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

		UserEntity userEntity = userMapper.mapUserDtoToUserEntity(dto);
		userEntity.setUserId(utils.generateUserId(30));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		userEntity.getAddresses().forEach(address -> {
			address.setAddressId(utils.generateAddressId(30));
			address.setUserEntity(userEntity);
		});
		
		UserEntity savedInDb = userRepository.save(userEntity);
		return userMapper.mapUserEntityToUserDto(savedInDb);
	}

	@Override
	public UserDto getUserById(String userId) {
		return userRepository.findByUserId(userId)
				.map(userMapper::mapUserEntityToUserDto)
				.orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+userId));

	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		return userRepository.findByUserId(userId)
			   .map(userEntity -> this.updateUser(userEntity, userDto))
			   .orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+userId)); 
	}
	
	@Override
	public void deleteUser(String userId) {
		Optional<UserEntity> optional = userRepository.findByUserId(userId);
		if(optional.isEmpty())
			throw new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+userId);
		
		userRepository.delete(optional.get());
	}
	
	@Override
	public List<UserDto> getAllUsers(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<UserEntity> pages = userRepository.findAll(pageable);
		List<UserEntity> userEntities = pages.getContent();
		return userMapper.mapListUserEntityToListUserDto(userEntities);
	}
	
	private UserDto updateUser(UserEntity userEntity, UserDto userDto) {
		userMapper.updateUser(userDto, userEntity);
		UserEntity updated = userRepository.save(userEntity);
		return userMapper.mapUserEntityToUserDto(updated);
	}
}