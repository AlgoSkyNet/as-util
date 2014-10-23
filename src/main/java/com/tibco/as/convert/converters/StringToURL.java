package com.tibco.as.convert.converters;

import java.net.MalformedURLException;
import java.net.URL;

public class StringToURL extends AbstractStringParser {

	@Override
	protected URL parse(String string) throws MalformedURLException {
		return new URL(string);
	}

}
