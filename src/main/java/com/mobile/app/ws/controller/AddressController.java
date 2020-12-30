package com.mobile.app.ws.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mobile.app.ws.dto.AddressDto;
import com.mobile.app.ws.mapper.AddressMapper;
import com.mobile.app.ws.mapper.AddressRequestMapper;
import com.mobile.app.ws.model.request.AddressRequest;
import com.mobile.app.ws.model.response.AddressResponse;
import com.mobile.app.ws.service.IAddressService;

@RestController
@RequestMapping("/users")
public class AddressController {

	private IAddressService addressService;
	
	private AddressMapper addressMapper;
	
	private AddressRequestMapper addressRequestMapper;

	public AddressController(IAddressService addressService, AddressMapper addressMapper,
			AddressRequestMapper addressRequestMapper) {
		super();
		this.addressService = addressService;
		this.addressMapper = addressMapper;
		this.addressRequestMapper = addressRequestMapper;
	}

	@GetMapping(path = "/{userId}/addresses",
			produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public List<AddressResponse> getAllUserAddressesById(@PathVariable("userId") String userId) {

		List<AddressDto> addresses = addressService.getAllUserAddressesById(userId);
		return addressMapper.mapListAddressDtoToListAddressResponse(addresses);		
	}
	
	@PostMapping("/{userId}/addresses")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable("userId") String userId,@RequestBody AddressRequest addressRequest) {
		AddressDto addressDto = addressRequestMapper.mapAddressRequestToAddressDto(addressRequest);
		AddressDto savedInDb = addressService.createAddress(userId, addressDto);
		AddressResponse addressResponse = addressMapper.mapAddressDtoToAddressResponse(savedInDb);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{addressId}").buildAndExpand(addressResponse.getAddressId()).toUri();
		return ResponseEntity.created(location).body(addressResponse);
	}
}
