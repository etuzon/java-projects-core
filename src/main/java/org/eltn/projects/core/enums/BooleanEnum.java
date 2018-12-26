package org.eltn.projects.core.enums;

public enum BooleanEnum {
	TRUE(Boolean.TRUE), FALSE(Boolean.FALSE), NA(null);
	
	private final Boolean value;
	
	private BooleanEnum(Boolean value) {
		this.value = value;
	}
	
	public Boolean asBoolean() {
		return value;
	}
}