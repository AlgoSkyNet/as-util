package com.tibco.as.convert.converters;

import java.net.URL;

import com.tibco.as.convert.IConverter;

public class URLToString implements IConverter {

	@Override
	public String convert(Object value) {
		return ((URL) value).toExternalForm();
	}

}
