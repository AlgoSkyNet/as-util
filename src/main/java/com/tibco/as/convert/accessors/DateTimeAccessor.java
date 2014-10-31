package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.Tuple;

public class DateTimeAccessor implements IAccessor {

	private String fieldName;

	public DateTimeAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getDateTime(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putDateTime(fieldName, (DateTime) value);
	}

}
