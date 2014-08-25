package com.tibco.as.convert.converters;

import java.net.URL;

import com.tibco.as.convert.IConverter;

public class URLToString implements IConverter<URL, String> {

	@Override
	public String convert(URL value) {
		return value.toExternalForm();
	}

}
