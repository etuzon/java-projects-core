package org.eltn.projects.core.expections;

import org.eltn.projects.core.utils.StringUtil;

public abstract class EltnExceptionBase extends Exception {

	private static final long serialVersionUID = 1L;

	
	public EltnExceptionBase() {
		super();
	}
	
	public EltnExceptionBase(String message) {
		super(message);
	}
	
	public EltnExceptionBase(Exception e) {
		super(e.getMessage() + "\nStacktrace:\n" + StringUtil.getExceptionStacktrace(e));
	}
}