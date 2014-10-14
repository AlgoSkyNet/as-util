package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.ShortFormat;

public class StringToShort extends AbstractNumberParser {

	public StringToShort(Field field) {
		super(field.getIntegerFormat(), new ShortFormat());
	}

	@Override
	protected Short convert(Number source) {
		return source.shortValue();
	}

}
