package com.tibco.as.util.convert.impl;

import java.util.Date;

public class LongToDate extends AbstractConverter<Long, Date> {

	@Override
	protected Date doConvert(Long source) {
		return new Date(source);
	}
}
