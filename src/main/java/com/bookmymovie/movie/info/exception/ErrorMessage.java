package com.bookmymovie.movie.info.exception;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {

	private Map<String,Object> errorMessages;
	private int status;
	
	public Map<String, Object> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(Map<String, Object> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
		
}
