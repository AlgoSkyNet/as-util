package com.tibco.as.convert.accessors;

import com.tibco.as.convert.IAccessor;

public class ObjectArrayAccessor implements IAccessor {

	private int index;

	public ObjectArrayAccessor(int index) {
		this.index = index;
	}

	@Override
	public Object get(Object object) {
		return ((Object[]) object)[index];
	}

	@Override
	public Object set(Object object, Object value) {
		((Object[]) object)[index] = value;
		return object;
	}

}
