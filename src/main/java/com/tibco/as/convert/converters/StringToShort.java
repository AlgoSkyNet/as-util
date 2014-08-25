package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.ShortFormat;

public class StringToShort extends AbstractNumberParser<Short> {

	public StringToShort(Attributes attributes) {
		super(attributes.get(Attribute.INTEGER), new ShortFormat());
	}

	@Override
	protected Short convert(Number number) {
		return number.shortValue();
	}

}
