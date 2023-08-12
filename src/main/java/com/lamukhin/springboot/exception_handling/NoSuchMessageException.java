package com.lamukhin.springboot.exception_handling;

public class NoSuchMessageException extends RuntimeException{

	public NoSuchMessageException(String message) {
		super(message);
	}
	
}
