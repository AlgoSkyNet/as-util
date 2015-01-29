package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

public class StringToISO8601 extends AbstractStringParser<Calendar> {

	@Override
	protected Calendar parse(String string) {
		return DatatypeConverter.parseDateTime(string);
	}

}