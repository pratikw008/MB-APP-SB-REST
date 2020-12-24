package com.mobile.app.ws.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mobile.app.ws.dto.AddressDto;
import com.mobile.app.ws.entity.AddressEntity;
import com.mobile.app.ws.model.request.AddressDetailsRequest;
import com.mobile.app.ws.model.response.AddressDetailsResponse;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	public AddressDto convertAddressEntityToDto(AddressEntity addressEntity);
	
	public AddressEntity convertAddressDtoToEntity(AddressDto addressDto);
	
	public AddressDto convertAddressDtlsRequestToDto(AddressDetailsRequest addressDetailsRequest);
	
	public AddressDetailsResponse convertAddressDtoToAddressDtlsResponse(AddressDto addressDto);
	
	public List<AddressDto> convertListEntityToListDto(List<AddressEntity> addresses);
	
	public List<AddressDetailsResponse> convertListDtoToListAddressResponse(List<AddressDto> addresses);
}
