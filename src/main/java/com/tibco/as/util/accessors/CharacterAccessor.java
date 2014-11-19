package com.tibco.as.util.accessors;

import com.tibco.as.space.Tuple;
import com.tibco.as.util.convert.IAccessor;

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
