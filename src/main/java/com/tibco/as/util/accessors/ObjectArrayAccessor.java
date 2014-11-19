package com.tibco.as.util.accessors;

import com.tibco.as.util.convert.IAccessor;

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
