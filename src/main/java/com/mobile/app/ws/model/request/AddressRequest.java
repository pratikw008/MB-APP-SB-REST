package com.mobile.app.ws.model.request;

import com.mobile.app.ws.entity.AddressType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AddressRequest {
	
	private String city;

	private String country;

	private String streetName;

	private String postalCode;

	private AddressType addressType;
}
