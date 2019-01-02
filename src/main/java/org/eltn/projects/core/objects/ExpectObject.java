package org.eltn.projects.core.objects;

import org.eltn.projects.core.enums.ExpectTypeEnum;

public class ExpectObject {
	private final ExpectTypeEnum type;
	private final String str;
	
	public ExpectObject(ExpectTypeEnum type, String str) {
		this.type = type;
		this.str = str;
	}

	public ExpectTypeEnum getType() {
		return type;
	}

	public String getStr() {
		return str;
	}
	
	public boolean isExpect(String input) {
		if (type == ExpectTypeEnum.CONTAINS) {
			return isContains(input);
		}
		
		if (type == ExpectTypeEnum.EQUALS) {
			return isEquals(input);
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