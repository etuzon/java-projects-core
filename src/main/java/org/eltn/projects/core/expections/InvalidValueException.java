package org.eltn.projects.core.expections;

public class InvalidValueException extends EltnExceptionBase {

	private static final long serialVersionUID = 1L;

	public InvalidValueException() {
		super();
	}
	
	public InvalidValueException(String message) {
		super(message);
	}
	
	public InvalidValueException(Exception e) {
		super(e);
	}
}