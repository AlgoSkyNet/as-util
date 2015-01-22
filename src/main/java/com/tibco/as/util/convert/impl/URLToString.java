package com.tibco.as.util.convert.impl;

import java.net.URL;

public class URLToString extends AbstractConverter<URL, String> {

	@Override
	protected String doConvert(URL source) {
		return source.toExternalForm();
	}

}
