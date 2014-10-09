package com.tibco.as.convert.array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class TupleToArrayConverter extends AbstractTupleToArrayConverter {

	private IConverter[] converters;

	public TupleToArrayConverter(ITupleAccessor[] accessors,
			IConverter[] converters) {
		super(accessors);
		this.converters = converters;
	}

	@Override
	protected Object convert(Object value, int index) throws ConvertException {
		return converters[index].convert(value);
	}

}
