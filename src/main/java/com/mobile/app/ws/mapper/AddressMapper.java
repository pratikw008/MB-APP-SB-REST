package com.mobile.app.ws.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mobile.app.ws.dto.AddressDto;
import com.mobile.app.ws.entity.AddressEntity;
import com.mobile.app.ws.model.response.AddressResponse;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AddressMapper {

	@Mapping(target = "userDto", ignore = true)
	public AddressDto mapAddressEntityToDto(AddressEntity addressEntity);

	@Mapping(target = "userEntity", ignore = true)
	@Mapping(target = "cratedAt", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	public AddressEntity mapAddressDtoToEntity(AddressDto addressDto);
	

	public AddressResponse mapAddressDtoToAddressResponse(AddressDto addressDto);

	public List<AddressDto> mapListEntityToListDto(List<AddressEntity> addresses);

	public List<AddressResponse> mapListAddressDtoToListAddressResponse(List<AddressDto> addresses);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "cratedAt", ignore = true)
	@Mapping(target = "lastModifiedAt", ignore = true)
	@Mapping(target = "userEntity", ignore = true)
	public void updateAddress(AddressDto addressDto, @MappingTarget AddressEntity addressEntity);
}
