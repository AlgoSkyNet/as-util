package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class BooleanAccessor extends TupleAccessor {

	public BooleanAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	protected Object get(Tuple tuple, String fieldName) {
		return tuple.getBoolean(fieldName);
	}

	@Override
	protected Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putBoolean(fieldName, (Boolean) value);
	}

}
