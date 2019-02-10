package org.eltn.projects.core.utils;

import org.eltn.projects.core.base.ObjectBase;
import org.eltn.projects.core.expections.InvalidValueException;

/********************************************
 * Arrays utility.
 * 
 * @author Eyal Tuzon
 *
 * @param <T> Object.
 */
public final class ArrayUtil <T> extends ObjectBase {
	
	private ArrayUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }
	
	/********************************************
	 * Get index of object in array.
	 *
	 * Throws InvalidParameterException in case array or object are null.
	 *
	 * @param arr Array.
	 * @param object Object.
	 * @param <T> Type of input array.
	 * @return Index of object in array.
	 * @throws InvalidValueException in case input array or objects are null.
	 */
	public static <T> int getIndex(T[] arr, T object) throws InvalidValueException {
		validateNotNull(arr);
		validateNotNull(object);
		
		for (int i=0; i < arr.length; i++) {		
			T temp = arr[i];
			
			if (object.equals(temp)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/********************************************
	 * Return true if object is in array.
	 * 
	 * @param arr Array.
	 * @param object Object.
	 * @param <T>  Type of input array.
	 * @return true in case object in array, otherwise return false.
	 * @throws InvalidValueException in case input array or objects are null.
	 */
	public static <T> boolean isObjectInArray(T[] arr, T object) throws InvalidValueException {
		return getIndex(arr, object) >= 0;
	}
}