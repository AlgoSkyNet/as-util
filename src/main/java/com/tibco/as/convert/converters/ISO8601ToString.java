package com.tibco.as.convert.converters;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.convert.IConverter;

public class ISO8601ToString implements IConverter {

	@Override
	public String convert(Object source) {
		return DatatypeConverter.printDateTime((Calendar) source);
	}

}