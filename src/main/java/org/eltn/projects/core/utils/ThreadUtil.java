package org.eltn.projects.core.utils;

public final class ThreadUtil {

	private ThreadUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

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