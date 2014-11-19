package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.util.convert.IConverter;

public class StringToISO8601 implements IConverter {

	@Override
	public Calendar convert(Object source) {
		return DatatypeConverter.parseDateTime((String) source);
	}

}