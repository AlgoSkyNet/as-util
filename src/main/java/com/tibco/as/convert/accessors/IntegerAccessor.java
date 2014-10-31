package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;
import com.tibco.as.space.Tuple;

public class IntegerAccessor implements IAccessor {

	private String fieldName;

	public IntegerAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getInt(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putInt(fieldName, (Integer) value);
	}

}
