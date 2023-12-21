package com.nagarro.exceptions;

import java.time.LocalDateTime;

public class CustomError {
	
	private String message;
	private int code;
	private LocalDateTime timestamp;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public CustomError(String message, int code, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
	}
	public CustomError() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomError [message=" + message + ", code=" + code + ", timestamp=" + timestamp + "]";
	}

}
