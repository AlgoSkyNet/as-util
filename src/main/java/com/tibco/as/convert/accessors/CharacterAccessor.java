package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;
import com.tibco.as.space.Tuple;

public class CharacterAccessor implements IAccessor {

	private String fieldName;

	public CharacterAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getChar(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putChar(fieldName, (Character) value);
	}

}
