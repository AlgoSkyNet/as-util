package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.IntegerFormat;

public class IntegerToString extends AbstractNumberFormatter<Integer> {

	public IntegerToString(Attributes attributes) {
		super(attributes.get(Attribute.INTEGER), new IntegerFormat());
	}

}
