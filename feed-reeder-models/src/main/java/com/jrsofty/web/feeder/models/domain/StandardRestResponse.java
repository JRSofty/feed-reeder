package com.jrsofty.web.feeder.models.domain;

public class StandardRestResponse {

	private int status = 200;
	private String message = "";
	
	public StandardRestResponse(String message, int status){
		this.message = message;
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getStatus() {
		return status;
	}
	
}
