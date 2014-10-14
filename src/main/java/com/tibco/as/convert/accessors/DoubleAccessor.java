package com.tibco.as.convert.accessors;

import com.tibco.as.space.Tuple;

public class DoubleAccessor implements ITupleAccessor {

	private String fieldName;

	public DoubleAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getDouble(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putDouble(fieldName, (Double) value);
	}

}
