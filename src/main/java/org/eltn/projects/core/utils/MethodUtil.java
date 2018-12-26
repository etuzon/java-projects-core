package org.eltn.projects.core.utils;

public final class MethodUtil {
	
	private MethodUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}
	
	public static String geStackTraceAsString(StackTraceElement stArr[]) {
		String result = "";

		for (StackTraceElement st : stArr) {
			result += st.toString() + "\n";
		}

		return result;
	}
}