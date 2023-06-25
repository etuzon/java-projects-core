package io.github.etuzon.projects.core.expections;

import java.io.Serial;

/**********************************************
 * Exception will be thrown when file or directory not succeed to be deleted.
 * 
 * @author Eyal Tuzon
 *
 */
public class FileNotDeletedException extends ExceptionBase {

	@Serial
	private static final long serialVersionUID = 1L;

	/**********************************************
	 * Constructor.
	 * 
	 */
	public FileNotDeletedException() {
		super();
	}
	
	/**********************************************
	 * Constructor.
	 * 
	 * @param message Exception message.
	 */
	public FileNotDeletedException(String message) {
		super(message);
	}
	
	/**********************************************
	 * Constructor.
	 * <p>
	 * Convert input exception as exception message and exception stacktrace.
	 * 
	 * @param e Input exception object that will be converted to exception message and stacktrace.
	 */
	public FileNotDeletedException(Exception e) {
		super(e);
	}
}