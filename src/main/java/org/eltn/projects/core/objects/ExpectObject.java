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
}