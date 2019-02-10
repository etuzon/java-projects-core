package org.eltn.projects.core.utils;

/******************************************
 * Print to console utility.
 * 
 * @author Eyal Tuzon
 *
 */
public final class PrintUtil {
	public static final int EXIT_STATUS_0 = 0;
	
	private PrintUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }
	
	/******************************************
	 * Print error with 'ERROR: ' in the beginning to console,
	 * and after that exit with status 1.
	 * 
	 * @param error Error message.
	 */
	public static void printErrorAndExit(String error) {
		System.out.println("ERROR: " + error);
		System.exit(1);
	}
	
	/******************************************
	 * Print exception message and stacktrace to console,
	 * and after that exit with input exit status.
	 * 
	 * @param e Exception.
	 * @param exitStatus Exit status.
	 */
	public static void printExceptionAndExit(Exception e, int exitStatus) {
		System.out.println(e.getMessage());
		System.out.println(StringUtil.getExceptionStacktrace(e));
		System.exit(exitStatus);
	}
}