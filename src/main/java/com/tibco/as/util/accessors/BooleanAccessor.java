package com.tibco.as.util.accessors;

import com.tibco.as.space.Tuple;
import com.tibco.as.util.convert.IAccessor;

public class BooleanAccessor implements IAccessor {

	private String fieldName;

	public BooleanAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getBoolean(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putBoolean(fieldName, (Boolean) value);
	}

}
