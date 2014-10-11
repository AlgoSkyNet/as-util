package com.tibco.as.accessors;

import com.tibco.as.space.Tuple;

public class IntegerAccessor implements ITupleAccessor {

	private String fieldName;

	public IntegerAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getInt(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putInt(fieldName, (Integer) value);
	}

}
