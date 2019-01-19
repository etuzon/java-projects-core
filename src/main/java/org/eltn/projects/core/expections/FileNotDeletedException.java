package org.eltn.projects.core.expections;

public class FileNotDeletedException extends EltnExceptionBase {

	private static final long serialVersionUID = 1L;

	public FileNotDeletedException() {
		super();
	}
	
	public FileNotDeletedException(String message) {
		super(message);
	}
	
	public FileNotDeletedException(Exception e) {
		super(e);
	}
}