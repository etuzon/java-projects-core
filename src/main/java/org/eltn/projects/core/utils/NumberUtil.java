package org.eltn.projects.core.utils;

public final class NumberUtil {
	
	private NumberUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	public static boolean isNumberInRange(int number, int min, int max) {
		return ((number >= min) && (number <= max));
	}

	public static String intToString(int number, int digitsAmount) {
		String numberStr = String.valueOf(number);

		if (numberStr.length() > digitsAmount) {
			return numberStr.substring(numberStr.length() - digitsAmount);
		}

		if (numberStr.length() < digitsAmount) {
			numberStr = addZeroBeforeString(numberStr, digitsAmount - numberStr.length());
		}

		return numberStr;
	}

	private static String addZeroBeforeString(String str, int zeroAmount) {
		for (int i=0; i < zeroAmount; i++) {
			str = "0" + str;
		}

		return str;
	}
}