package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;
import com.tibco.as.space.Tuple;

public class ShortAccessor implements IAccessor {

	private String fieldName;

	public ShortAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getShort(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putShort(fieldName, (Short) value);
	}

}
