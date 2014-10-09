package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.ShortFormat;

public class ShortToString extends AbstractNumberFormatter {

	public ShortToString(Attributes attributes) {
		super(attributes.get(Attribute.INTEGER), new ShortFormat());
	}

}
