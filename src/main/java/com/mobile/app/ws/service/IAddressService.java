package com.mobile.app.ws.service;

import java.util.List;

import com.mobile.app.ws.dto.AddressDto;

public interface IAddressService {
	 
	public List<AddressDto> getAllUserAddressesById(String userId);
}