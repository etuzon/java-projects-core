package org.eltn.projects.core.utils;

import org.eltn.projects.core.base.BaseObject;

public final class ArrayUtil <T> extends BaseObject {
	
	private ArrayUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }
	
	public static <T> int getIndex(T[] arr, T object) {
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
	
	public static <T> boolean isObjectInArray(T[] arr, T object) {
		return getIndex(arr, object) >= 0;
	}
}