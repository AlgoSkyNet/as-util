package com.tibco.as.convert.array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class ArrayToTupleConverter extends AbstractArrayToTupleConverter {

	private IConverter[] converters;

	public ArrayToTupleConverter(ITupleAccessor[] accessors,
			IConverter[] converters) {
		super(accessors);
		this.converters = converters;
	}

	@Override
	protected Object convert(Object value, int index) throws ConvertException {
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
