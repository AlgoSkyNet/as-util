package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;
import com.tibco.as.space.Tuple;

public class BlobAccessor implements IAccessor {

	private String fieldName;

	public BlobAccessor(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public Object get(Object tuple) {
		return ((Tuple) tuple).getBlob(fieldName);
	}

	@Override
	public Object set(Object tuple, Object value) {
		return ((Tuple) tuple).putBlob(fieldName, (byte[]) value);
	}

}
