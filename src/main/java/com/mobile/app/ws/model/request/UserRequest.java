package com.mobile.app.ws.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private List<AddressRequest> addresses;
}
