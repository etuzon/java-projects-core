package io.github.etuzon.projects.core.expections;

import io.github.etuzon.projects.core.utils.StringUtil;

import java.io.Serial;

/**************************************
 * Base exception.
 * 
 * @author Eyal Tuzon
 *
 */
public abstract class ExceptionBase extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	/**************************************
	 * Constructor.
	 * 
	 */
	protected ExceptionBase() {
		super();
	}
	
	/**************************************
	 * Constructor.
	 * 
	 * @param message Exception message.
	 */
	protected ExceptionBase(String message) {
		super(message);
	}
	
	/**************************************
	 * Constructor.
	 * <p>
	 * Convert input exception as exception message and exception stacktrace.
	 * 
	 * @param e Input exception object that will be converted to exception message and stacktrace.
	 */
	protected ExceptionBase(Exception e) {
		super(e.getMessage() + "\nStacktrace:\n" + StringUtil.getExceptionStacktrace(e));
	}
}