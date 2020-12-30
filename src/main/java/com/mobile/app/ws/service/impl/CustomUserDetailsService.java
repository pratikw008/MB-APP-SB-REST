package com.mobile.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.entity.UserEntity;
import com.mobile.app.ws.mapper.UserMapper;
import com.mobile.app.ws.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	private UserMapper userMapper;
	
	public CustomUserDetailsService(UserRepository userRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException("Invalid Credentials");
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}
	
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException("Invalid Credentials");
		
		UserDto userDto = userMapper.mapUserEntityToUserDto(userEntity);
		
		return userDto;
	}
}
