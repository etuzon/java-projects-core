package org.eltn.projects.core.utils;

/********************************
 * Numbers utility.
 * 
 * @author Eyal Tuzon.
 *
 */
public final class NumberUtil {

    private NumberUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }

    /************************************
     * Check if number is in range of minimum and maximum.
     * 
     * @param number A number.
     * @param min Minimum range.
     * @param max Maximum range.
     * @return true in case number is in range of minimum and maximum.
     */
    public static boolean isNumberInRange(int number, int min, int max) {
        return ((number >= min) && (number <= max));
    }

    /************************************
     * Convert int to string with digits amount limitation.
     * In case digitsAmount value is bigger from the number digits,
     * than the number will be returned as string with 0 before that will
     * fill the amount of digits.
     * 
     * In case digitsAmount is smaller from the number digits,
     * than the number will be returned as sub string with input digits amount.
     * 
     * Ex: number: 1234, digitsAmount: 6, return 001234.
     * Ex: number: -1234, digitsAmount: 6, return -001234. 
     * Ex: number: 1234, digitsAmount: 2, return 34.
     * Ex: number: -1234, digitsAmount: 2, return -34.
     * 
     * @param number A number.
     * @param digitsAmount Amount of digits.
     * @return Number as string with 'digitsAmount' digits amount.
     */
    public static String intToString(int number, int digitsAmount) {
        String numberStr = String.valueOf(number);
        
        if (number < 0) {
            numberStr = removeMinusBeforeNumberStr(numberStr);
        }
        
        if (numberStr.length() > digitsAmount) {
            numberStr = numberStr.substring(numberStr.length() - digitsAmount);
           
            return addMinusToNumberStrIfNegativeNumber(number, numberStr);
        }

        if (numberStr.length() < digitsAmount) {
            numberStr = addZeroBeforeString(numberStr, digitsAmount - numberStr.length());
        }
        
        return addMinusToNumberStrIfNegativeNumber(number, numberStr);
    }
    
    private static String addMinusToNumberStrIfNegativeNumber(int number, String numberStr) {
        if (number < 0) {
            return "-" + numberStr;
        }
        
        return numberStr;
    }
    
    private static String removeMinusBeforeNumberStr(String numberStr) {
        return numberStr.substring(1);
    }
    
    private static String addZeroBeforeString(String str, int zeroAmount) {
        for (int i = 0; i < zeroAmount; i++) {
            str = "0" + str;
        }

        return str;
    }
}