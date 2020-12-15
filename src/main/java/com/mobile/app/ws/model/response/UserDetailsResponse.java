package com.mobile.app.ws.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsResponse {
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
}
