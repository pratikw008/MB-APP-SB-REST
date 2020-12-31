package com.mobile.app.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobile.app.ws.dto.AddressDto;
import com.mobile.app.ws.entity.AddressEntity;
import com.mobile.app.ws.entity.UserEntity;
import com.mobile.app.ws.exceptions.InvalidUserIdException;
import com.mobile.app.ws.mapper.AddressMapper;
import com.mobile.app.ws.model.response.ErrorMessages;
import com.mobile.app.ws.repository.AddressRepository;
import com.mobile.app.ws.repository.UserRepository;
import com.mobile.app.ws.service.IAddressService;
import com.mobile.app.ws.util.Utils;

@Service
public class AddressServiceImpl implements IAddressService {

	private AddressRepository addressRepository;

	private UserRepository userRepository;

	private AddressMapper addressMapper;

	private Utils utils;
	
	public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository,
			AddressMapper addressMapper, Utils utils) {
		super();
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.addressMapper = addressMapper;
		this.utils = utils;
	}

	@Override
	public List<AddressDto> getAllUserAddressesById(String userId) {
		List<AddressEntity> addresses = userRepository.findByUserId(userId)
				.map(addressRepository::findAllByUserEntity)
				.orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+userId));
		return addressMapper.mapListEntityToListDto(addresses);
	}

	@Override
	public AddressDto createAddress(String userId, AddressDto addressDto) {
		return userRepository.findByUserId(userId)
							 .map(userEntity -> saveAddress(userEntity,addressDto))
							 .orElseThrow(() -> new InvalidUserIdException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()+userId ));
	}

	private AddressDto saveAddress(UserEntity userEntity, AddressDto addressDto) {
		AddressEntity addressEntity = addressMapper.mapAddressDtoToEntity(addressDto);
		addressEntity.setAddressId(utils.generateAddressId(30));
		addressEntity.setUserEntity(userEntity);
		
		AddressEntity savedInDb = addressRepository.save(addressEntity);
		return addressMapper.mapAddressEntityToDto(savedInDb);
	}
	
	@Override
	public AddressDto updateAddress(String addressId, AddressDto addressDto) {
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		addressMapper.updateAddress(addressDto, addressEntity);
		addressEntity = addressRepository.save(addressEntity);
		return addressMapper.mapAddressEntityToDto(addressEntity);
	}
}
