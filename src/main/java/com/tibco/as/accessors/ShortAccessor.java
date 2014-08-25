package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class ShortAccessor extends TupleAccessor {

	public ShortAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	protected Object get(Tuple tuple, String fieldName) {
		return tuple.getShort(fieldName);
	}

	@Override
	protected Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putShort(fieldName, (Short) value);
	}

}
