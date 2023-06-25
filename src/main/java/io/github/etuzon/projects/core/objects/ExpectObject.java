package io.github.etuzon.projects.core.objects;

import io.github.etuzon.projects.core.enums.ExpectTypeEnum;

/**********************************************
 * Expect object contain expect type and the string that should be expected.
 *
 * @author Eyal Tuzon
 *
 */
public record ExpectObject(ExpectTypeEnum type, String str) {

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
			return !isEquals(input);
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