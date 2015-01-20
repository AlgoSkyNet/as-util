package com.tibco.as.util.convert.impl;

public class CharacterToString extends AbstractConverter<Character, String> {

	@Override
	protected String doConvert(Character source) {
		return String.valueOf(source);
	}
}
