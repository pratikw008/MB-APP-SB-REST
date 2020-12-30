package com.mobile.app.ws.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.entity.UserEntity;
import com.mobile.app.ws.model.response.UserResponse;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {
	
	public UserResponse mapUserDtoToUserResponse(UserDto userDto);
	
	@Mapping(target = "password", ignore = true)
	public UserDto mapUserEntityToUserDto(UserEntity userEntity);
	
	@Mapping(target = "cratedAt", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	public UserEntity mapUserDtoToUserEntity(UserDto userDto);
	
	public List<UserDto> mapListUserEntityToListUserDto(List<UserEntity> users);
	
	public List<UserResponse> mapListUserDtoToListUserResponse(List<UserDto> dtos);
	
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "cratedAt", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	@Mapping(target = "encryptedPassword", ignore = true)
	@Mapping(target = "addresses", ignore = true)
	void updateUser(UserDto userDto, @MappingTarget UserEntity userEntity);
}
