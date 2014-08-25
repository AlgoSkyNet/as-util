package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.DoubleFormat;

public class DoubleToString extends AbstractNumberFormatter<Double> {

	public DoubleToString(Attributes attributes) {
		super(attributes.get(Attribute.DECIMAL), new DoubleFormat());
	}

}
