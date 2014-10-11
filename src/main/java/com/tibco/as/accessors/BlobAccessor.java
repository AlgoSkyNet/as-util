package com.tibco.as.accessors;

import com.tibco.as.space.Tuple;

public class BlobAccessor implements ITupleAccessor {

	private String fieldName;

	public BlobAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Tuple tuple) {
		return tuple.getBlob(fieldName);
	}

	@Override
	public Object set(Tuple tuple, Object value) {
		return tuple.putBlob(fieldName, (byte[]) value);
	}

}
