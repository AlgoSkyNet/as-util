package com.tibco.as.convert.converters;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class ISO8601ToString implements IConverter<Calendar, String> {

	@Override
	public String convert(Calendar source) throws ConvertException {
		return DatatypeConverter.printDateTime(source);
	}

}