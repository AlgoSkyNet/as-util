package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class IntegerAccessor extends TupleAccessor {

	public IntegerAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	protected Object get(Tuple tuple, String fieldName) {
		return tuple.getInt(fieldName);
	}

	@Override
	protected Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putInt(fieldName, (Integer) value);
	}

}
