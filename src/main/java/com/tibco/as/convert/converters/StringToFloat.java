package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.FloatFormat;

public class StringToFloat extends AbstractNumberParser {

	public StringToFloat(Attributes attributes) {
		super(attributes.get(Attribute.DECIMAL), new FloatFormat());
	}

	@Override
	protected Float convert(Number number) {
		return number.floatValue();
	}

}
