package com.mobile.app.ws.entity;

public enum AddressType {
	
	BILLING("Billing"),SHIPPING("Shipping");
	
	private String type;

	AddressType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
