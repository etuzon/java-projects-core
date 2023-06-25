package io.github.etuzon.projects.core.utils;

import io.github.etuzon.projects.core.base.ObjectBase;
import io.github.etuzon.projects.core.expections.InvalidValueException;

/******************************************************
 * IPv4 utility.
 * 
 * @author Eyal Tuzon
 *
 */
public final class Ip4Util extends ObjectBase {

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

		String[] splitStr = str.split("\\.");

		if (splitStr.length != 4)
			return false;

		for (String temp : splitStr) {
			try {
				Integer octet = Integer.valueOf(temp);

				if (!isByteValue(octet)) {
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
	 * @param octets Array of 4 int cells.
	 * @return String in IPv4 structure. Return null in case input array is not IPv4.
	 * @throws InvalidValueException in case input array is null.
	 */
	public static String toIPv4(int[] octets) throws InvalidValueException {
		validateNotNull(octets);

		if (octets.length != 4) {
			return null;
		}

		for (int i=0; i < 4; i++) {
			if (!isByteValue(octets[i])) {
				return null;
			}
		}

		return octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3];
	}

	private static boolean isByteValue(Integer num) {
		return num >= 0 && num <= 255;
	}
}