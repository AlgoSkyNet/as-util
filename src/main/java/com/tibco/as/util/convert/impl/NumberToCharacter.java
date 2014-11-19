package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToCharacter implements IConverter {

	@Override
	public Character convert(Object value) {
		return (char) ((Number) value).shortValue();
	}

}
