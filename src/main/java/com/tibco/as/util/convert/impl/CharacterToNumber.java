package com.tibco.as.util.convert.impl;

public class CharacterToNumber extends AbstractConverter<Character, Number> {

	@Override
	protected Number doConvert(Character source) {
		return Character.getNumericValue(source);
	}

}
