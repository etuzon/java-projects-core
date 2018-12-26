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
				int octat = Integer.valueOf(temp);
				
				if (isNumberBetween0To255(octat) == false) {
					return false;
				}	
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return true;
	}
	
	public static String valueOf(int octats[]) {
		validateNotNull(octats);
		
		if (octats.length != 4) {
			return null;
		}
		
		String ip = "";
		
		for (int i=0; i < 4; i++) {
			if (isNumberBetween0To255(octats[i]) == false) {
				return null;
			}
			
			ip = ip + String.valueOf(octats[i]) + ".";
		}
		
		return StringUtil.removeLastChar(ip);
	}
	
	private static boolean isNumberBetween0To255(int num) {
		return (num >= 0) && (num <=  255);
	}
}