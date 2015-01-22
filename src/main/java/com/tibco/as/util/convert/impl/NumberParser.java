package com.tibco.as.util.convert.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.util.log.LogFactory;

public class NumberParser extends AbstractConverter<String, Number> {

	private Logger log = LogFactory.getLog(NumberParser.class);
	private NumberFormat format;

	public NumberParser(NumberFormat format) {
		this.format = format;
	}

	@Override
	protected Number doConvert(String source) {
		try {
			return format.parse(source);
		} catch (ParseException e) {
			log.log(Level.SEVERE, "Could not parse", e);
			return null;
		}
	}

}
