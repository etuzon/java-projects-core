package io.github.etuzon.projects.core.expections;

import java.io.Serial;

/********************************************
 * Exception will be thrown when input value is invalid.
 * 
 * @author Eyal Tuzon
 *
 */
public class InvalidValueException extends ExceptionBase {

	@Serial
	private static final long serialVersionUID = 1L;

	/********************************************
	 * Constructor.
	 * 
	 */
	public InvalidValueException() {
		super();
	}
	
	/********************************************
	 * Constructor.
	 * 
	 * @param message Exception message.
	 */
	public InvalidValueException(String message) {
		super(message);
	}
	
	/********************************************
	 * Constructor.
	 * <p>
	 * Convert input exception as exception message and exception stacktrace.
	 * 
	 * @param e Input exception object that will be converted to exception message and stacktrace.
	 */
	public InvalidValueException(Exception e) {
		super(e);
	}
}