package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public abstract class AbstractConverter<S, T> implements IConverter {

	@SuppressWarnings({ "unchecked" })
	@Override
	public T convert(Object source) {
		if (source == null) {
			return null;
		}
		return doConvert((S) source);
	}

	protected abstract T doConvert(S source);

}
