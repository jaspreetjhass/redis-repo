package org.jp.exceptions;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8596482743679469732L;
	
	public ApplicationException(String message) {
		super(message);
	}

}
