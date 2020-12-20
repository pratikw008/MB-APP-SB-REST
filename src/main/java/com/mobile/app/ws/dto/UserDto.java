package com.mobile.app.ws.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 888246019388392882L;
	
	private Long Id;
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;

	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	private Boolean emailVerificationStatus = false;
}
