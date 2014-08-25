package com.tibco.as.convert.array;

import java.lang.reflect.Array;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

@SuppressWarnings("rawtypes")
public class TupleToArrayConverter<T> extends AbstractTupleToArrayConverter<T> {

	private IConverter[] converters;
	private Class<T> arrayComponentType;

	public TupleToArrayConverter(ITupleAccessor[] accessors,
			IConverter[] converters, Class<T> arrayComponentType) {
		super(accessors);
		this.converters = converters;
		this.arrayComponentType = arrayComponentType;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object convert(Object value, int index) throws ConvertException {
		return converters[index].convert(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T[] newInstance() {
		return (T[]) Array.newInstance(arrayComponentType, converters.length);
	}

}
