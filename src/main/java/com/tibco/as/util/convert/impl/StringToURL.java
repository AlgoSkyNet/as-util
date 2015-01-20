package com.tibco.as.util.convert.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.util.log.LogFactory;

public class StringToURL extends AbstractStringParser<URL> {

	private Logger log = LogFactory.getLog(StringToURL.class);

	@Override
	protected URL parse(String string) {
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "Could not parse URL", e);
			return null;
		}
	}

}
