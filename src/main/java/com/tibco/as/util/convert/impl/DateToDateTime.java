package com.tibco.as.util.convert.impl;

import java.util.Date;

import com.tibco.as.space.DateTime;

public class DateToDateTime extends AbstractConverter<Date, DateTime> {

	@Override
	protected DateTime doConvert(Date source) {
		return DateTime.create(source.getTime());
	}

}
