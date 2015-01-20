package com.tibco.as.util.convert.impl;

public class NumberToCharacter extends AbstractConverter<Number, Character> {

	@Override
	protected Character doConvert(Number source) {
		return (char) source.shortValue();
	}

}
