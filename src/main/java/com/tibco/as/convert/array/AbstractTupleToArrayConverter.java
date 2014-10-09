package com.tibco.as.convert.array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.TupleToObjectConverter;

public abstract class AbstractTupleToArrayConverter extends
		TupleToObjectConverter {

	private int length;

	public AbstractTupleToArrayConverter(ITupleAccessor[] accessors) {
		super(accessors);
		this.length = accessors.length;
	}

	@Override
	protected void set(Object element, Object value, int index) {
		((Object[]) element)[index] = value;
	}

	@Override
	protected Object[] newInstance() {
		return new Object[length];
	}

}
