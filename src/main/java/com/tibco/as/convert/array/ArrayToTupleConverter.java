package com.tibco.as.convert.array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

@SuppressWarnings("rawtypes")
public class ArrayToTupleConverter<T> extends AbstractArrayToTupleConverter<T> {

	private IConverter[] converters;

	public ArrayToTupleConverter(ITupleAccessor[] accessors,
			IConverter[] converters) {
		super(accessors);
		this.converters = converters;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object convert(T value, int index) throws ConvertException {
		if (value == null) {
			return null;
		}
		IConverter converter = converters[index];
		if (converter == null) {
			return null;
		}
		return converter.convert(value);
	}

}
