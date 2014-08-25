package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToCharacter implements IConverter<Number, Character> {

	@Override
	public Character convert(Number value) {
		return (char) value.shortValue();
	}

}
