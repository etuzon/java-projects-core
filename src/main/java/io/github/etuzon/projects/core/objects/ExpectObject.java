package io.github.etuzon.projects.core.objects;

import io.github.etuzon.projects.core.enums.ExpectTypeEnum;

/**********************************************
 * Expect object contain expect type and the string that should be expected.
 * 
 * @author Eyal Tuzon
 *
 */
public class ExpectObject {
	private final ExpectTypeEnum type;
	private final String str;
	
	/**********************************************
	 * Constructor.
	 * 	
	 * @param type Expect type.
	 * @param str String that should be expected.
	 */
	public ExpectObject(ExpectTypeEnum type, String str) {
		this.type = type;
		this.str = str;
	}

	/**********************************************
	 * Get expect type.
	 * 
	 * @return Expect type.
	 */
	public ExpectTypeEnum getType() {
		return type;
	}

	/**********************************************
	 * Get string to be expected.
	 * 
	 * @return String to be expected.
	 */
	public String getStr() {
		return str;
	}
	
	/**********************************************
	 * Check input string meets the condition (ExpectTypeEnum) on expect string. 
	 * 
	 * @param input Input string that will be checked if it is match condition on expect string.
	 * @return true in case input string meet expect condition, otherwise return false.
	 */
	public boolean isExpect(String input) {
		if (type == ExpectTypeEnum.CONTAINS) {
			return isContains(input);
		}
		
		if (type == ExpectTypeEnum.EQUALS) {
			return isEquals(input);
		}

		if (type == ExpectTypeEnum.DIFFERENT) {
		    return isEquals(input) == false;
		}
		
		return false;
	}
	
	private boolean isContains(String input) {
		return input.contains(str);
	}
	
	private boolean isEquals(String input) {
		return input.equals(str);
	}
}