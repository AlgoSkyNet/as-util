package com.tibco.as.util.convert.impl;

public class BooleanToNumber extends AbstractConverter<Boolean, Number> {

	@Override
	protected Number doConvert(Boolean source) {
		return source ? 1 : 0;
	}

}
