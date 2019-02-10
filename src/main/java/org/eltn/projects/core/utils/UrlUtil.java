package org.eltn.projects.core.utils;

import org.apache.commons.lang3.StringUtils;

/**********************************************
 * URL utility.
 * 
 * @author Eyal Tuzon
 *
 */
public final class UrlUtil {

	private UrlUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	/**********************************************
	 * Get hostname from URL.
	 * 
	 * @param url URL.
	 * @return Hostname that is part of URL.
	 */
	public static String getHostFromUrl(String url) {
		url = subUrlPrefix(url);
		url = subUrlSlash(url);
		url = subUrlColon(url);

		return url;
	}

	private static String subUrlPrefix(String url) {
		final String URL_PREFIX = "://";

		int index = url.indexOf(URL_PREFIX);

		if (index > 0) {
			index += URL_PREFIX.length();

			if (index < url.length()) {
				return url.substring(index);
			}
		}

		return url;
	}

	private static String subUrlSlash(String url) {
		return StringUtils.substringBefore(url, "/");
	}

	private static String subUrlColon(String url) {
		return StringUtils.substringBefore(url, ":");
	}
}