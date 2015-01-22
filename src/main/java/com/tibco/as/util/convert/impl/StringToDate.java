package com.tibco.as.util.convert.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.util.log.LogFactory;

public class StringToDate extends AbstractStringParser<Date> {

	private Logger log = LogFactory.getLog(StringToDate.class);
	private DateFormat format;

	public StringToDate(DateFormat format) {
		this.format = format;
	}

	@Override
	protected Date parse(String string) {
		try {
			return format.parse(string);
		} catch (ParseException e) {
			log.log(Level.SEVERE, "Could not parse date", e);
			return null;
		}
	}

}
