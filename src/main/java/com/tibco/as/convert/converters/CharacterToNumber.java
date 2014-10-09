package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class CharacterToNumber implements IConverter {

	@Override
	public Number convert(Object value) {
		return Character.getNumericValue((Character) value);
	}

}
