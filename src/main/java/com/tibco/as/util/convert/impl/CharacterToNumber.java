package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class CharacterToNumber implements IConverter {

	@Override
	public Number convert(Object value) {
		return Character.getNumericValue((Character) value);
	}

}
