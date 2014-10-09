package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.LongFormat;

public class LongToString extends AbstractNumberFormatter {

	public LongToString(Attributes attributes) {
		super(attributes.get(Attribute.INTEGER), new LongFormat());
	}

}
