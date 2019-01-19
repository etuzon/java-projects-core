package org.eltn.projects.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eltn.projects.core.base.BaseObject;
import org.eltn.projects.core.objects.ExpectObject;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public final class StringUtil extends BaseObject {
	public static final String REPLACE_STR = "$REPLACE_STR$";

	private StringUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	public static List<String> removeEmptyElementsFromList(List<String> list) {
		list.removeAll(Arrays.asList("", null));
		return list;
	}

	public static boolean isExpect(String input, List<ExpectObject> expectsList) {
		for (ExpectObject expect : expectsList) {
			if (expect.isExpect(input)) {
				return true;
			}
		}

		return false;
	}

	public static List<String> getContainList(String str, List<String> containList) {
		List<String> result = new ArrayList<String>();

		for (String contain : containList) {
			if (str.contains(contain)) {
				result.add(contain);
			}
		}

		return result;
	}

	public static List<String> split(String str, char ch) {
		String arr[] = str.split(String.valueOf(ch), -1);
		return ListUtil.asList(arr);
	}

	public static String replace(String str, String... args) throws Exception {
		if (args == null) {
			throw new Exception("args for [" + str + "] cannot be null");
		}

		int count = StringUtils.countMatches(str, REPLACE_STR);
		String argsArr[] = args.clone();

		if (argsArr.length == 0) {
			throw new Exception("Replace for [" + str + "] not contain any arguments to be replaced");
		}

		if (count != argsArr.length) {
			throw new Exception("Replace for [" + str + "] contain [" + argsArr.length + "] args and should contain ["
					+ count + "] args");
		}

		String result = str;

		for (String arg : argsArr) {
			int index = result.indexOf(REPLACE_STR);

			result = result.substring(0, index) + arg + result.substring(index + REPLACE_STR.length());
		}

		return result;
	}

	public static String xmlToString(Element element) {
		return new XMLOutputter().outputString(element);
	}

	public static String getExceptionStacktrace(Exception e) {
		StringBuilder sb = new StringBuilder();

		for (StackTraceElement stackElement : e.getStackTrace()) {
			sb.append(stackElement.getClassName()).append(".").
			append(stackElement.getMethodName()).append("(").append(stackElement.getFileName())
			.append(":").append(stackElement.getLineNumber()).append(")\n");
		}

		return sb.toString();
	}

	public static String removeLastChar(String str) {
		if (str.length() <= 1) {
			return "";
		}

		return str.substring(0, str.length() - 1);
	}
}