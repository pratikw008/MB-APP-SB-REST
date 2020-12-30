package com.mobile.app.ws.model.response;

import com.mobile.app.ws.entity.AddressType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
	
	private String addressId;
	
	private String city;

	private String country;

	private String streetName;

	private String postalCode;

	private AddressType addressType; 
}
