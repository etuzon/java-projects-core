package io.github.tuzon.projects.core.expections;

/********************************************
 * Exception will be thrown when input value is invalid.
 * 
 * @author Eyal Tuzon
 *
 */
public class InvalidValueException extends EltnExceptionBase {

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
	 * 
	 * Convert input exception as exception message and exception stacktrace.
	 * 
	 * @param e Input exception object that will be converted to exception message and stacktrace.
	 */
	public InvalidValueException(Exception e) {
		super(e);
	}
}