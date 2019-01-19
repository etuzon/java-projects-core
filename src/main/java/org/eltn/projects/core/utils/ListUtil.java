package org.eltn.projects.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.eltn.projects.core.base.BaseObject;

public final class ListUtil extends BaseObject {
	
	private ListUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}
	
	/*******************************
	 * Get list of of objects in containList that exists in list
	 * @param containList
	 * @param list
	 * @return
	 */
	public static <T> List<T> getContainList(List<T> list, List<T> containList) {
		List<T> result = new ArrayList<T>();

		for (T obj : containList) {
			if (list.contains(obj)) {
				result.add(obj);
			}
		}

		return result;
	}

	/*******************************
	 * Get list of of objects in containList that not exists in list
	 * @param containList
	 * @param list
	 * @return
	 */
	public static <T> List<T> getNotContainList(List<T> list, List<T> containList) {
		List<T> result = new ArrayList<T>();

		for (T obj : containList) {
			if (list.contains(obj) == false) {
				result.add(obj);
			}
		}

		return result;
	}

	public static <T> List<T> asList(T[] array, int startIndex) {
		validateNotNull(array);

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
		StringBuilder sb = new StringBuilder();
		
		for (T obj : list) {
			sb.append(obj.toString()).append("\n");
		}
		
		String result = sb.toString();
		
		if (result.isEmpty() == false) {
			result = StringUtil.removeLastChar(result);
		}

		return result;
	}
	
	public static <T> List<T> asList(@SuppressWarnings("unchecked") T... args) {
		List<T> result = new ArrayList<T>();
	
		for (T arg : args) {
			result.add(arg);
		}
		
		return result;
	}
	
	public static <T> List<T> clone(List<T> list) {
		List<T> result = new ArrayList<T>();
		
		for (T member : list) {
			result.add(member);
		}
		
		return result;
	}
}