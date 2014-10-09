package com.tibco.as.convert.converters;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class StringToISO8601 implements IConverter {

	@Override
	public Calendar convert(Object source) throws ConvertException {
		return DatatypeConverter.parseDateTime((String) source);
	}

}