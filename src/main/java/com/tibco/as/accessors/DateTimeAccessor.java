package com.tibco.as.accessors;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.Tuple;

public class DateTimeAccessor implements ITupleAccessor {

	private String fieldName;

	public DateTimeAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object get(Tuple tuple) {
		return tuple.getDateTime(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putDateTime(fieldName, (DateTime) value);
	}

}
