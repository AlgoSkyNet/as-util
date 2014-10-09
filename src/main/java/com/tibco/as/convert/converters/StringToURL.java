package com.tibco.as.convert.converters;

import java.net.MalformedURLException;
import java.net.URL;

import com.tibco.as.convert.ConvertException;

public class StringToURL extends AbstractStringParser {

	@Override
	protected URL parse(String string) throws ConvertException {
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			throw new ConvertException(e, string);
		}
	}

}
