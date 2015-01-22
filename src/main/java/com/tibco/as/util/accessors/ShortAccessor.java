package com.tibco.as.util.accessors;

import com.tibco.as.space.Tuple;
import com.tibco.as.util.convert.IAccessor;

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
		if (value == null) {
			return null;
		}
		return ((Tuple) tuple).putShort(fieldName, (Short) value);
	}

}
