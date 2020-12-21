package com.mobile.app.ws.model.response;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field. Please check documention for required field"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record not found with provided ID"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address not verified");
	
	private String errorMessage;
	
	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
