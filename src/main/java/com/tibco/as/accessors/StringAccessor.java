package com.tibco.as.accessors;

import com.tibco.as.space.Tuple;

public class StringAccessor implements ITupleAccessor {

	private String fieldName;

	public StringAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getString(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putString(fieldName, (String) value);
	}

}
