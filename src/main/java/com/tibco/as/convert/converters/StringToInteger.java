package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.IntegerFormat;

public class StringToInteger extends AbstractNumberParser<Integer> {

	public StringToInteger(Attributes attributes) {
		super(attributes.get(Attribute.INTEGER), new IntegerFormat());
	}

	@Override
	protected Integer convert(Number number) {
		return number.intValue();
	}

}
