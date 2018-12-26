package org.eltn.projects.core.utils;

public final class ThreadUtil {
	
	private ThreadUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}
	
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}