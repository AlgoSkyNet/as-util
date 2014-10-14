package com.tibco.as.convert.accessors;

import com.tibco.as.space.Tuple;

public class ShortAccessor implements ITupleAccessor {

	private String fieldName;

	public ShortAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getShort(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putShort(fieldName, (Short) value);
	}

}
