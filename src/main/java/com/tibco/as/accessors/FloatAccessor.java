package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class FloatAccessor extends TupleAccessor {

	public FloatAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	protected Object get(Tuple tuple, String fieldName) {
		return tuple.getFloat(fieldName);
	}

	@Override
	protected Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putFloat(fieldName, (Float) value);
	}

}
