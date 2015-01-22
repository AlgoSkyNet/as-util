package com.tibco.as.util.accessors;

import com.tibco.as.space.Tuple;
import com.tibco.as.util.convert.IAccessor;

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
