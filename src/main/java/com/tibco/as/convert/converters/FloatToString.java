package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.FloatFormat;

public class FloatToString extends AbstractNumberFormatter<Float> {

	public FloatToString(Attributes attributes) {
		super(attributes.get(Attribute.DECIMAL), new FloatFormat());
	}

}
