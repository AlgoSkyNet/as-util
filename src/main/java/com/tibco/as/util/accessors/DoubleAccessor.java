package com.tibco.as.util.accessors;

import com.tibco.as.space.Tuple;
import com.tibco.as.util.convert.IAccessor;

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
