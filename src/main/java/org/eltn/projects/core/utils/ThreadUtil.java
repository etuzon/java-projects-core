package org.eltn.projects.core.utils;

/********************************************
 * Thread utility.
 * 
 * @author Eyal Tuzon.
 *
 */
public final class ThreadUtil {
    public static final long SECOND_1 = 1000;
	private ThreadUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	/********************************************
	 * Sleep for X milliseconds.
	 * The method let user not need add try/catch.
	 * 
	 * @param ms Sleep for X milliseconds.
	 *        In case ms is <= 0 sleep will not happen.
	 */
	public static void sleep(long ms) {
		if (ms <= 0) {
			return;
		}
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}