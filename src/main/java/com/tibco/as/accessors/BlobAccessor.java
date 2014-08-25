package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public class BlobAccessor extends TupleAccessor {

	public BlobAccessor(FieldDef fieldDef) {
		super(fieldDef);
	}

	@Override
	protected Object get(Tuple tuple, String fieldName) {
		return tuple.getBlob(fieldName);
	}
	
	@Override
	protected Object set(Tuple tuple, String fieldName, Object value) {
		return tuple.putBlob(fieldName, (byte[]) value);
	}

}
