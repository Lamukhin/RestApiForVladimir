package com.lamukhin.springboot.exception_handling;

public class AccessException extends RuntimeException{

	public AccessException(String message) {
		super(message);
	}
	
}
