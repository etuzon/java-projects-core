package org.eltn.projects.core.base;

import org.eltn.projects.core.expections.InvalidValueException;

/***************************************
 * Base object that can be inherit by every class.
 * 
 * @author Eyal Tuzon
 * 
 */
public abstract class ObjectBase {
    /***************************************
     * Validate that array not null.
     * Method throws InvalidValueException in case array is null.
     * 
     * @param arr array.
     * @param <T> Type of input array.
     * @throws InvalidValueException in case array is null.
     */
    protected static <T> void validateNotNull(T[] arr) throws InvalidValueException {
        if (arr == null) {
            throw new InvalidValueException("Array is null");
        }
    }

    /***************************************
     * Validate that object is not null.
     * Method throws InvalidValueException in case object is null.
     * 
     * @param object Object.
     * @param <T> Type of input 'object'.
     * @throws InvalidValueException in case object is null.
     */
    protected static <T> void validateNotNull(T object) throws InvalidValueException {
        if (object == null) {
            throw new InvalidValueException("Object is null");
        }
    }
    
    /***************************************
     * Validate that number is not negative.
     * 
     * @param number A number.
     * @throws InvalidValueException in case number is negative.
     */
    protected static void validateNotNegative(long number) throws InvalidValueException {
        if (number < 0) {
            throw new InvalidValueException("Number [" + number + "] is negative");
        }
    }
    
    /***************************************
     * Validate that number is not negative.
     * 
     * @param number A number.
     * @throws InvalidValueException in case number is negative.
     */
    protected static void validateNotNegative(int number) throws InvalidValueException {
        validateNotNegative((long) number);
    }
}