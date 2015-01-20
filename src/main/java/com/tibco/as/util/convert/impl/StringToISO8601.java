package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

public class StringToISO8601 extends AbstractConverter<String, Calendar> {

	@Override
	protected Calendar doConvert(String source) {
		return DatatypeConverter.parseDateTime(source);
	}

}