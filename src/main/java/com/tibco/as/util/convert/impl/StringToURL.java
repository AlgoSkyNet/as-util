package com.tibco.as.util.convert.impl;

import java.net.MalformedURLException;
import java.net.URL;

public class StringToURL extends AbstractStringParser {

	@Override
	protected URL parse(String string) throws MalformedURLException {
		return new URL(string);
	}

}
