package com.lamukhin.springboot.exception_handling;

public class RegistrationException extends RuntimeException{

	public RegistrationException(String message) {
		super(message);
	}
	
}
