package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class LongAccessor extends TupleAccessor {

	public LongAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	public Object get(Tuple tuple, String fieldName) {
		return tuple.getLong(fieldName);
	}

	@Override
	public Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putLong(fieldName, (Long) value);
	}

}
