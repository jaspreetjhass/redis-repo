package com.example.demo.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 7852792618362088525L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
