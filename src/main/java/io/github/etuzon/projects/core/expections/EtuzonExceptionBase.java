package io.github.etuzon.projects.core.expections;

import io.github.etuzon.projects.core.utils.StringUtil;

/**************************************
 * Base exception.
 * 
 * @author Eyal Tuzon
 *
 */
public abstract class EtuzonExceptionBase extends Exception {

	private static final long serialVersionUID = 1L;

	/**************************************
	 * Constructor.
	 * 
	 */
	public EtuzonExceptionBase() {
		super();
	}
	
	/**************************************
	 * Constructor.
	 * 
	 * @param message Exception message.
	 */
	public EtuzonExceptionBase(String message) {
		super(message);
	}
	
	/**************************************
	 * Constructor.
	 * 
	 * Convert input exception as exception message and exception stacktrace.
	 * 
	 * @param e Input exception object that will be converted to exception message and stacktrace.
	 */
	public EtuzonExceptionBase(Exception e) {
		super(e.getMessage() + "\nStacktrace:\n" + StringUtil.getExceptionStacktrace(e));
	}
}