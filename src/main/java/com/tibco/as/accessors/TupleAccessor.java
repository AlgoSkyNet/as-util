package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public abstract class TupleAccessor implements ITupleAccessor {

	private FieldDef fieldDef;

	public TupleAccessor(FieldDef fieldDef) {
		this.fieldDef = fieldDef;
	}

	@Override
	public FieldDef getFieldDef() {
		return fieldDef;
	}

	@Override
	public Object get(Tuple tuple) {
		return get(tuple, fieldDef.getName());
	}

	protected abstract Object get(Tuple tuple, String fieldName);

	@Override
	public Object set(Tuple object, Object value) {
		return set(object, fieldDef.getName(), value);
	}

	protected abstract Object set(Tuple tuple, String fieldName, Object value);
}
