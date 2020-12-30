package com.mobile.app.ws.mapper;

import org.mapstruct.Mapper;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.model.request.UserRequest;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressRequestMapper.class})
public interface UserRequestMapper {
	
	@Mapping(target = "emailVerificationStatus", ignore = true)
	@Mapping(target = "emailVerificationToken", ignore = true)
	@Mapping(target = "encryptedPassword", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userId", ignore = true)
	public UserDto mapUserRequestToUserDto(UserRequest userRequest);
	
	
}
