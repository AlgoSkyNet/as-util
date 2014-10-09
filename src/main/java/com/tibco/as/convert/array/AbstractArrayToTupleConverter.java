package com.tibco.as.convert.array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ObjectToTupleConverter;

public abstract class AbstractArrayToTupleConverter extends
		ObjectToTupleConverter {

	public AbstractArrayToTupleConverter(ITupleAccessor[] accessors) {
		super(accessors);
	}

	@Override
	protected Object get(Object object, int index) throws ConvertException {
		Object[] array = (Object[]) object;
		if (array == null || array.length <= index) {
			return null;
		}
		return convert(array[index], index);
	}

	protected abstract Object convert(Object value, int index)
			throws ConvertException;

}
