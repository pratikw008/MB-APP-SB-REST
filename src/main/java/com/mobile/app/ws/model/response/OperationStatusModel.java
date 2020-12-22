package com.mobile.app.ws.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationStatusModel {
	
	private String oprationName;
	
	private String oprationStatusResult;
}
