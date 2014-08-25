package com.tibco.as.convert.converters;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class StringToISO8601 implements IConverter<String, Calendar> {

	@Override
	public Calendar convert(String source) throws ConvertException {
		return DatatypeConverter.parseDateTime(source);
	}

}