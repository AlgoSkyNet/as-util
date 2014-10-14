package com.tibco.as.convert.accessors;

import com.tibco.as.space.Tuple;

public class BooleanAccessor implements ITupleAccessor {

	private String fieldName;

	public BooleanAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getBoolean(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putBoolean(fieldName, (Boolean) value);
	}

}
