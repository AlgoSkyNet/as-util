package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;
import com.tibco.as.space.Tuple;

public class DoubleAccessor implements IAccessor {

	private String fieldName;

	public DoubleAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getDouble(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putDouble(fieldName, (Double) value);
	}

}
