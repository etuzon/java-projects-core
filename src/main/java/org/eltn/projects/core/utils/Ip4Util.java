package org.eltn.projects.core.utils;

import org.eltn.projects.core.base.BaseObject;
import org.eltn.projects.core.expections.InvalidValueException;

/******************************************************
 * IPv4 utility.
 * 
 * @author Eyal Tuzon.
 *
 */
public final class Ip4Util extends BaseObject {

	private Ip4Util() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	/******************************************************
	 * Check if input String is a valid IPv4.
	 * 
	 * @param str String
	 * @return true in case input String is valid IPv4, else return false.
	 * @throws InvalidValueException in case input String is null.
	 */
	public static boolean isValidIp(String str) throws InvalidValueException {
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

	/******************************************************
	 * Convert int array to String in IPv4 structure.
	 * 
	 * @param octats Array of 4 int cells.
	 * @return String in IPv4 structure. Return null in case input array is not IPv4.
	 * @throws InvalidValueException in case input array is null.
	 */
	public static String toIPv4(int octats[]) throws InvalidValueException {
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