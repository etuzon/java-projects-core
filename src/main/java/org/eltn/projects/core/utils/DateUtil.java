package org.eltn.projects.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtil {
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";
	
	public static final long MS_IN_SECOND = 1000;
	public static final long MS_IN_MINUTE = 60 * MS_IN_SECOND;
	public static final long MS_IN_HOUR = 60 * MS_IN_MINUTE;
	public static final long MS_IN_DAY = 24 * MS_IN_HOUR;
	
	private DateUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }
	
	public static String getCurrentDate() {
		return getCurrentDate(DEFAULT_DATE_FORMAT);
	}
	
	public static String getCurrentDate(String dateFormat) {
		LocalDateTime current = LocalDateTime.now();
		return current.format(DateTimeFormatter.ofPattern(dateFormat));
	}

	public String getCurrentDateForDirectory() {
		String date = getCurrentDate();
		date = date.replaceAll("/", "_");
		date = date.replaceAll(":", "_");
		date = date.replace(" ", "_");
		return date;
	}

	public static boolean isTimeout(long startTimeMillis, long timeoutMillis) {
		if (timeoutMillis == 0L) {
			return false;
		}
		return System.currentTimeMillis() - startTimeMillis > timeoutMillis;
	}

	public static Date getDateBeforeXDays(int days) {
		long ms = System.currentTimeMillis() - days * MS_IN_DAY;
		return new Date(ms);
	}	
}