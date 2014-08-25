package com.tibco.as.convert;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.space.Tuple;

public abstract class TupleToObjectConverter<T> implements IConverter<Tuple, T> {

	private ITupleAccessor[] accessors;

	public TupleToObjectConverter(ITupleAccessor[] accessors) {
		this.accessors = accessors;
	}

	@Override
	public T convert(Tuple tuple) throws ConvertException {
		T result = newInstance();
		for (int index = 0; index < accessors.length; index++) {
			ITupleAccessor accessor = accessors[index];
			if (accessor == null) {
				continue;
			}
			Object value = accessor.get(tuple);
			if (value == null) {
				continue;
			}
			Object convertedValue = convert(value, index);
			if (convertedValue == null) {
				continue;
			}
			set(result, convertedValue, index);
		}
		return result;
	}

	protected abstract T newInstance();

	protected abstract Object convert(Object value, int index)
			throws ConvertException;

	protected abstract void set(T element, Object value, int index);

}
