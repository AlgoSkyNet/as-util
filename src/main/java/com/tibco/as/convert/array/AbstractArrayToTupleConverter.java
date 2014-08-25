package com.tibco.as.convert.array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ObjectToTupleConverter;

public abstract class AbstractArrayToTupleConverter<T> extends
		ObjectToTupleConverter<T[]> {

	public AbstractArrayToTupleConverter(ITupleAccessor[] accessors) {
		super(accessors);
	}

	@Override
	protected Object get(T[] array, int index) throws ConvertException {
		if (array == null || array.length <= index) {
			return null;
		}
		return convert(array[index], index);
	}

	protected abstract Object convert(T value, int index)
			throws ConvertException;

}
