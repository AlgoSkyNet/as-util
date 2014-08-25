package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attribute;
import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.format.DoubleFormat;

public class StringToDouble extends AbstractNumberParser<Double> {

	public StringToDouble(Attributes attributes) {
		super(attributes.get(Attribute.DECIMAL), new DoubleFormat());
	}

	@Override
	protected Double convert(Number number) {
		return number.doubleValue();
	}

}
