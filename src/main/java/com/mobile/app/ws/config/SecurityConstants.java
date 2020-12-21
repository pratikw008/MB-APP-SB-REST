package com.mobile.app.ws.config;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 300000;			//864000000;  // 10 Days
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final String HEADER_STRING = "Authorization";
	
	public static final String SIGN_UP_URL = "/users";
	
	public static final String LOGIN_URL = "/users/login";
	
	/*
	 * public static final String TOKEN_SECRET = Base64.getEncoder()
	 * .encodeToString(SecurityConstants.getTokenSecret().getBytes());
	 */
	
	public static String getTokenSecret() {
		AppProperties appProperties = SpringApplicationContext.getBean(AppProperties.class);
		return appProperties.getTokenSecret();
	}
}
