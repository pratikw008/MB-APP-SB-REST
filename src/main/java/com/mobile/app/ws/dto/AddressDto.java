package com.mobile.app.ws.dto;

import java.io.Serializable;

import com.mobile.app.ws.entity.AddressType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto implements Serializable{

	private static final long serialVersionUID = 6504737532476757960L;

	private Long id;

	private String addressId;

	private String city;

	private String country;

	private String streetName;

	private String postalCode;

	private AddressType addressType; 

	private UserDto userDto;
}
