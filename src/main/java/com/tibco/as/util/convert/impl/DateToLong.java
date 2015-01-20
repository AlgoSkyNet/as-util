package com.tibco.as.util.convert.impl;

import java.util.Date;

public class DateToLong extends AbstractConverter<Date, Long> {

	@Override
	protected Long doConvert(Date source) {
		return source.getTime();
	}

}
