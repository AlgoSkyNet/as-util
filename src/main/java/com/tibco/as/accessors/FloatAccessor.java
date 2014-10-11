package com.tibco.as.accessors;

import com.tibco.as.space.Tuple;

public class FloatAccessor implements ITupleAccessor {

	private String fieldName;

	public FloatAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getFloat(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putFloat(fieldName, (Float) value);
	}

}
