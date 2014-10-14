package com.tibco.as.convert.accessors;

import com.tibco.as.space.Tuple;

public class CharacterAccessor implements ITupleAccessor {

	private String fieldName;

	public CharacterAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object get(Tuple tuple) {
		return tuple.getChar(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putChar(fieldName, (Character) value);
	}

}
