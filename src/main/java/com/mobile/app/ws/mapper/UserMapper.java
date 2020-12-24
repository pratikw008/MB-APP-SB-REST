package com.mobile.app.ws.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.entity.UserEntity;
import com.mobile.app.ws.model.request.UserDetailsRequest;
import com.mobile.app.ws.model.response.UserDetailsResponse;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {
	
	@Mapping(source = "password", target = "encryptedPassword")
	public UserDto convertUserDetailsRequestToUserDto(UserDetailsRequest userDetailsRequest);
	
	public UserDetailsResponse convertUserDtoToUserDetailsResponse(UserDto userDto);
	
	public UserDto convertUserEntityToUserDto(UserEntity userEntity);
	
	public UserEntity convertUserDtoToUserEntity(UserDto userDto);
	
	public List<UserDto> convertListUserEntityToListUserDto(List<UserEntity> users);
	
	public List<UserDetailsResponse> convertListDtoToListUserDtlsResponse(List<UserDto> dtos);
}
