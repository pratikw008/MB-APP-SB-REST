package com.mobile.app.ws.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	
	private LocalDateTime timestamp;
	
	private int status;
	
	private String error;
	
	private String message;
	
	private String path;
}
