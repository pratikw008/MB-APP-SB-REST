package com.mobile.app.ws.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDetailsRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private List<AddressDetailsRequest> addresses;
}
