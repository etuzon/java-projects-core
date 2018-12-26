package org.eltn.projects.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.eltn.projects.core.base.BaseObject;

public final class ListUtil<T> extends BaseObject {
	
	private ListUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}
	
	public static String getFirstContainString(String str, List<String> containList) {
		for (String contains : containList) {
			if (str.contains(contains)) {
				return contains;
			}
		}
		
		return null;
	}

	/*******************************
	 * Get list of of objects in containList that not exists in list
	 * @param containList
	 * @param list
	 * @return
	 */
	public static <T> List<T> getNotContainList(List<T> containList, List<T> list) {
		List<T> notContainList = new ArrayList<T>();

		for (T obj : containList) {
			if (list.contains(obj) == false) {
				notContainList.add(obj);
			}
		}

		return notContainList;
	}

	public static <T> List<T> arrayToList(T[] array, int startIndex) {
		validateNotNull(array);
		
		if (array == null)
			return null;

		List<T> result = new ArrayList<T>();

		for (int i = startIndex; i < array.length; i++) {
			result.add(array[i]);
		}

		return result;
	}
	
	public static <T> List<T> removeDuplication(List<T> list) {
		List<T> uniqueList = new ArrayList<T>();
		
		for (T obj : list) {
			if (uniqueList.contains(obj) == false) {
				uniqueList.add(obj);
			}
		}
		
		return uniqueList;
	}
	
	public static <T> String getMultilineStringFromList(List<T> list) {
		String result = "";

		for (T obj : list) {
			String str = obj.toString();
			result += str + "\n";
		}
		
		if (result.isEmpty() == false) {
			result = StringUtil.removeLastChar(result);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> asList(T... args) {
		List<T> resultList = new ArrayList<T>();
	
		for (T arg : args) {
			resultList.add(arg);
		}
		
		return resultList;
	}
	
	public static <T> List<T> clone(List<T> list) {
		List<T> resultList = new ArrayList<T>();
		
		for (T member : list) {
			resultList.add(member);
		}
		
		return resultList;
	}
}