package com.tibco.as.convert.accessors;

import com.tibco.as.space.Tuple;

public class LongAccessor implements ITupleAccessor {

	private String fieldName;

	public LongAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getLong(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putLong(fieldName, (Long) value);
	}

}
