package io.github.tuzon.projects.core.enums;

/************************************
 * Boolean enum.
 * 
 * Values are:
 * TRUE: true.
 * FALSE: false.
 * NA: Value that is not boolean.
 * 
 * @author Eyal Tuzon
 *
 */
public enum BooleanEnum {
	TRUE(Boolean.TRUE, "true"), FALSE(Boolean.FALSE, "false"), NA(null, "NA");
	
	private final Boolean value;
	private final String name;
	
	private BooleanEnum(Boolean value, String name) {
		this.value = value;
		this.name = name;
	}
	
	/************************************
	 * Convert from String to BooleanEnum.
	 * No case sensitive.
	 * 	
	 * @param str String that represent boolean, true or false.
	 * @return BooleanEnum that its value is:
	 *         TRUE in case String is 'true',
	 *         FALSE in case String 'false,
	 *         NA in case String is not represent boolean.
	 */
	public static BooleanEnum asBooleanEnum(String str) {
		str = str.toLowerCase();

		if (str.equals("true")) {
			return BooleanEnum.TRUE;
		}

		if (str.equals("false")) {
			return BooleanEnum.FALSE;
		}

		return BooleanEnum.NA;
	}
	
    /************************************
     * Convert from BooleanEnum to Boolean.
     * @return Boolean value in case BooleanEnum is TRUE or FALSE.
     *         In case BooleanEnum is NA, than return null.         
     */
	public Boolean asBoolean() {
		return value;
	}
	
	/************************************
	 * Convert from Boolean enum to String.
	 * 
	 * @return String representative of boolean.
	 *         Return NA in case Boolean enum value is NA.
	 */
	public String toString() {
		return name;
	}
}