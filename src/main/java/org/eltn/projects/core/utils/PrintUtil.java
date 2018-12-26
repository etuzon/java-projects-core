package org.eltn.projects.core.utils;

public final class PrintUtil {
	public static final int EXIT_STATUS_0 = 0;
	
	private PrintUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }
	
	public static void printErrorAndExit(String str) {
		System.out.println("ERROR: " + str);
		System.exit(1);
	}
	
	public static void printExceptionAndExit(Exception e, int exitStatus) {
		System.out.println(e.getMessage());
		System.out.println(StringUtil.getExceptionStacktrace(e));
		System.exit(exitStatus);
	}
}