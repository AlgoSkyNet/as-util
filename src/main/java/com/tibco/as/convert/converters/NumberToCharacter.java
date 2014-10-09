package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToCharacter implements IConverter {

	@Override
	public Character convert(Object value) {
		return (char) ((Number) value).shortValue();
	}

}
