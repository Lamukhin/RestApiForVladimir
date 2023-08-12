package com.lamukhin.springboot.exception_handling;

public class LoginException extends RuntimeException{

	public LoginException(String message) {
		super(message);
	}
	
}
