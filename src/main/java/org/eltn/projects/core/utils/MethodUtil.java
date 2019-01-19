package org.eltn.projects.core.utils;

public final class MethodUtil {
	
	private MethodUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}
	
	public static String geStackTraceAsString(StackTraceElement stArr[]) {
		StringBuilder sb = new StringBuilder();

		for (StackTraceElement st : stArr) {
			sb.append(st.toString()).append("\n");
		}

		return sb.toString();
	}
}