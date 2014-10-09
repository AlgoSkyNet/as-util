package com.tibco.as.convert.converters;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public abstract class AbstractStringParser implements IConverter {

	@Override
	public Object convert(Object object) throws ConvertException {
		String string = (String) object;
		if (string.isEmpty()) {
			return null;
		}
		return parse(string);
	}

	protected abstract Object parse(String string) throws ConvertException;

}
