package io.github.etuzon.projects.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.etuzon.projects.core.base.ObjectBase;
import io.github.etuzon.projects.core.expections.InvalidValueException;

/*********************************************
 * List utility.
 * 
 * @author Eyal Tuzon
 *
 */
public final class ListUtil extends ObjectBase {
	
	private ListUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}
	
	/*******************************
	 * Get sub list of of objects needed be contains in containList.
	 * 
	 * @param containList List of objects that object need to be in it to be returned.
	 * @param list List of objects that will be returned only if they exists in containList.
	 * @param <T> Type of class of objects in input and output List.
	 * @return List of objects that exists in 'list' and in 'containList'.
	 */
	public static <T> List<T> getContainList(List<T> list, List<T> containList) {
		List<T> result = new ArrayList<>();

		for (T obj : list) {
			if (containList.contains(obj)) {
				result.add(obj);
			}
		}

		return result;
	}

	/*******************************
	 * Get list of of objects in list that not exists in containList.
	 * 
	 * @param containList List of objects that should not be returned.
	 * @param list List of objects.
	 * @param <T> Type of class of objects in input and output List.
	 * @return List of objects that exists in 'list but not in 'containList'.
	 */
	public static <T> List<T> getNotContainList(List<T> list, List<T> containList) {
		List<T> result = new ArrayList<>();

		for (T obj : list) {
			if (!containList.contains(obj)) {
				result.add(obj);
			}
		}

		return result;
	}

	/*******************************
	 * Convert sub array from start index to List.
	 * 
	 * @param array Array of objects.
	 * @param startIndex Start index of sub array.
	 * @param <T> Type of class of objects in input and output List.
	 * @return List of sub array that start from startIndex.
	 *         Return empty list in case startIndex is equal or bigger than array length.
	 * @throws InvalidValueException in case array is null or startIndex is negative.
	 */
	public static <T> List<T> asList(T[] array, int startIndex) throws InvalidValueException {
		validateNotNull(array);
		validateNotNegative(startIndex);
		try {
			return new ArrayList<>(Arrays.asList(array).subList(startIndex, array.length));
		} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
			return new ArrayList<>();
		}
	}
	
	/*******************************
	 * remove duplication from list.
	 * 
	 * @param list List of objects.
	 * @param <T> Type of class of objects in input and output List.
	 * @return Input list without duplications.
	 * @throws InvalidValueException in case input list is null.
	 */
	public static <T> List<T> removeDuplication(List<T> list) throws InvalidValueException {
	    validateNotNull(list);
	    
		List<T> uniqueList = new ArrayList<>();
		
		for (T obj : list) {
			if (!uniqueList.contains(obj)) {
				uniqueList.add(obj);
			}
		}
		
		return uniqueList;
	}
	
	/*******************************
	 * Get multi line string of object's toString() in list.
	 * 
	 * @param list List of objects.
	 * @param <T> Type of class of objects in input and output List.
	 * @return Multi line string of object's toString() in list.
	 */
	public static <T> String getMultilineStringFromList(List<T> list) {
		StringBuilder sb = new StringBuilder();
		
		for (T obj : list) {
			sb.append(obj.toString()).append("\n");
		}
		
		String result = sb.toString();
		
		if (!result.isEmpty()) {
			result = StringUtil.removeLastChar(result);
		}

		return result;
	}
	
	/*******************************
	 * Convert input parameters to List.
	 * 
	 * @param args Parameters from the same type.
	 * @param <T> Type of class of objects in input args and output List.
	 * @return List of the parameters.
	 */
	@SafeVarargs
	public static <T> List<T> asList(T... args) {
		return new ArrayList<>(Arrays.asList(args));
	}
	
	/*******************************
	 * Clone input list.
	 * 
	 * @param list List of objects.
	 * @param <T> Type of class of objects in input and output List.
	 * @return Clone of input list.
	 */
	public static <T> List<T> clone(List<T> list) {
		return new ArrayList<>(list);
	}
}