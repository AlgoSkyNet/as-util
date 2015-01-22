package com.tibco.as.util.accessors;

import com.tibco.as.util.convert.IAccessor;

@SuppressWarnings("unchecked")
public class ArrayAccessor<T> implements IAccessor {

	private int index;

	public ArrayAccessor(int index) {
		this.index = index;
	}

	@Override
	public Object get(Object object) {
		return ((T[]) object)[index];
	}

	@Override
	public Object set(Object object, Object value) {
		((T[]) object)[index] = (T) value;
		return object;
	}

}
