package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.util.convert.IConverter;

public class ISO8601ToString implements IConverter {

	@Override
	public String convert(Object source) {
		return DatatypeConverter.printDateTime((Calendar) source);
	}

}