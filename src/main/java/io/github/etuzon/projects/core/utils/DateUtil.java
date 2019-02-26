package io.github.etuzon.projects.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/***************************************************
 * Date and time utility.
 * 
 * @author Eyal Tuzon
 *
 */
public final class DateUtil {
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";
	
	public static final long MS_IN_SECOND = 1000;
	public static final long MS_IN_MINUTE = 60 * MS_IN_SECOND;
	public static final long MS_IN_HOUR = 60 * MS_IN_MINUTE;
	public static final long MS_IN_DAY = 24 * MS_IN_HOUR;
	
	private DateUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }
	
	/***************************************************
	 * Get current date in 'DEFAULT_DATE_FORMAT' format.
	 * 
	 * @return Current date in 'DEFAULT_DATE_FORMAT' format.
	 */
	public static String getCurrentDate() {
		return getCurrentDate(DEFAULT_DATE_FORMAT);
	}
	
	/***************************************************
	 * Get current date in input date format.
	 * 
	 * @param dateFormat Date format.
	 * @return Current date in input date format.
	 */
	public static String getCurrentDate(String dateFormat) {
		LocalDateTime current = LocalDateTime.now();
		return current.format(DateTimeFormatter.ofPattern(dateFormat));
	}

	/***************************************************
	 * Get current date in string that is valid string for file name.
	 * 
	 * @return Current date in string that is valid string for file name.
	 */
	public String getCurrentDateForFile() {
		String date = getCurrentDate();
		date = date.replaceAll("/", "_");
		date = date.replaceAll(":", "_");
		date = date.replace(" ", "_");
		return date;
	}

	/***************************************************
	 * Check if is time is over for start time and timeout range.
	 * 
	 * @param startTimeMillis Start time.
	 * @param timeoutMillis Timeout range.
	 * @return true in case time is over for start time and timeout range.
	 */
	public static boolean isTimeout(long startTimeMillis, long timeoutMillis) {
		if (timeoutMillis == 0L) {
			return false;
		}
		return System.currentTimeMillis() - startTimeMillis > timeoutMillis;
	}

	/***************************************************
	 * Get Date before X days from current date.
	 * 
	 * @param days Number of days.
	 * @return Date object that its value is the date before X days from current date.
	 */
	public static Date getDateBeforeXDays(int days) {
		long ms = System.currentTimeMillis() - days * MS_IN_DAY;
		return new Date(ms);
	}	
}