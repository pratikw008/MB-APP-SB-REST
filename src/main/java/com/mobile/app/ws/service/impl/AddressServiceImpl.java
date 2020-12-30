package com.mobile.app.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobile.app.ws.dto.AddressDto;
import com.mobile.app.ws.entity.AddressEntity;
import com.mobile.app.ws.exceptions.InvalidUserIdException;
import com.mobile.app.ws.mapper.AddressMapper;
import com.mobile.app.ws.model.response.ErrorMessages;
import com.mobile.app.ws.repository.AddressRepository;
import com.mobile.app.ws.repository.UserRepository;
import com.mobile.app.ws.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	
	private AddressRepository addressRepository;
	
	private UserRepository userRepository;
	
	private AddressMapper addressMapper;
	
	public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository,
			AddressMapper addressMapper) {
		super();
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.addressMapper = addressMapper;
	}

	@Override
	public List<AddressDto> getAllUserAddressesById(String userId) {
		List<AddressEntity> addresses = userRepository.findByUserId(userId)
					  .map(addressRepository::findAllByUserEntity)
					  .orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+userId));
		return addressMapper.mapListEntityToListDto(addresses);
	}

}
