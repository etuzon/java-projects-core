package org.eltn.projects.core.enums;

public enum BooleanEnum {
	TRUE(Boolean.TRUE, "true"), FALSE(Boolean.FALSE, "false"), NA(null, "NA");
	
	private final Boolean value;
	private final String name;
	
	private BooleanEnum(Boolean value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public BooleanEnum asBooleanEnum(String str) {
		str = str.toLowerCase();

		if (str.equals("true")) {
			return BooleanEnum.TRUE;
		}

		if (str.equals("false")) {
			return BooleanEnum.FALSE;
		}

		return BooleanEnum.NA;
	}
	
	public Boolean asBoolean() {
		return value;
	}
	
	public String toString() {
		return name;
	}
}