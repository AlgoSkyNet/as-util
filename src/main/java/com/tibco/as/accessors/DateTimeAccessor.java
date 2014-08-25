package com.tibco.as.accessors;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class DateTimeAccessor extends TupleAccessor {

	public DateTimeAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	protected Object get(Tuple tuple, String fieldName) {
		return tuple.getDateTime(fieldName);
	}

	@Override
	protected Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putDateTime(fieldName, (DateTime) value);
	}

}
