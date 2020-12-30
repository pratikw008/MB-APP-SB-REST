package com.mobile.app.ws.mapper;

import org.mapstruct.Mapper;

import com.mobile.app.ws.dto.AddressDto;
import com.mobile.app.ws.model.request.AddressRequest;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressRequestMapper {
	
	@Mapping(target = "addressId", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userDto", ignore = true)
	public AddressDto mapAddressRequestToAddressDto(AddressRequest addressRequest);
}
