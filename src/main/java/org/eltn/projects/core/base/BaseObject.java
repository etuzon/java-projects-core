package org.eltn.projects.core.base;

import java.security.InvalidParameterException;

public abstract class BaseObject {
	protected static <T> void validateNotNull(T[] arr) {
		if (arr == null) {
			throw new InvalidParameterException("Array is null");
		}
	}
	
	protected static <T> void validateNotNull(T object) {
		if (object == null) {
			throw new InvalidParameterException("Object is null");
		}
	}
}