package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.LongFormat;

public class StringToLong extends AbstractNumberParser {

	public StringToLong(Attributes attributes) {
		super(attributes.get(Attribute.INTEGER), new LongFormat());
	}

	@Override
	protected Long convert(Number number) {
		return number.longValue();
	}
}
