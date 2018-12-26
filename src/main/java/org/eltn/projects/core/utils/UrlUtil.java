package org.eltn.projects.core.utils;

import org.apache.commons.lang3.StringUtils;

public final class UrlUtil {

	private UrlUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

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