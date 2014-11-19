package com.tibco.as.util.convert.impl;

import java.net.URL;

import com.tibco.as.util.convert.IConverter;

public class URLToString implements IConverter {

	@Override
	public String convert(Object value) {
		return ((URL) value).toExternalForm();
	}

}
