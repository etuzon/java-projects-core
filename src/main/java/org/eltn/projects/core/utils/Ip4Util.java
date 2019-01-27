package org.eltn.projects.core.utils;

import org.eltn.projects.core.base.BaseObject;

public final class Ip4Util extends BaseObject {

	private Ip4Util() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	public static boolean isValidIp(String str) {
		validateNotNull(str);

		String splitStr[] = str.split("\\.");

		if (splitStr.length != 4)
			return false;

		for (String temp : splitStr) {
			try {
				Integer octat = Integer.valueOf(temp);

				if (isNumberBetween0To255(octat) == false) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return true;
	}

	public static String toIPv4(int octats[]) {
		validateNotNull(octats);

		if (octats.length != 4) {
			return null;
		}

		for (int i=0; i < 4; i++) {
			if (isNumberBetween0To255(octats[i]) == false) {
				return null;
			}
		}

		return String.valueOf(octats[0]) + "." + String.valueOf(octats[1]) + "." + String.valueOf(octats[2]) + "."
				+ String.valueOf(octats[3]);
	}

	private static boolean isNumberBetween0To255(Integer num) {
		return (num >= 0) && (num <= 255);
	}
}