package org.eltn.projects.core.expections;

import org.eltn.projects.core.utils.StringUtil;

/**************************************
 * Base exception.
 * 
 * @author Eyal Tuzon.
 *
 */
public abstract class EltnExceptionBase extends Exception {

	private static final long serialVersionUID = 1L;

	/**************************************
	 * Constructor.
	 * 
	 */
	public EltnExceptionBase() {
		super();
	}
	
	/**************************************
	 * Constructor.
	 * 
	 * @param message Exception message.
	 */
	public EltnExceptionBase(String message) {
		super(message);
	}
	
	/**************************************
	 * Constructor.
	 * 
	 * Convert input exception as exception message and exception stacktrace.
	 * 
	 * @param e Input exception object that will be converted to exception message and stacktrace.
	 */
	public EltnExceptionBase(Exception e) {
		super(e.getMessage() + "\nStacktrace:\n" + StringUtil.getExceptionStacktrace(e));
	}
}