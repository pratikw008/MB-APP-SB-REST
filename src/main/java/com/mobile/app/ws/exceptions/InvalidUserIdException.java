package com.mobile.app.ws.exceptions;

public class InvalidUserIdException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4278078431167615347L;
	
	public InvalidUserIdException(String message) {
		super(message);
	}

}
