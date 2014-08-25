package com.tibco.as.convert.converters;

import java.text.Format;
import java.text.ParseException;

import com.tibco.as.convert.ConvertException;

public abstract class AbstractParser<T> extends AbstractStringParser<T> {

	Format format;

	public AbstractParser(Format format) {
		this.format = format;
	}
	
	protected Object parseObject(String string) throws ConvertException {
		try {
			return format.parseObject(string);
		} catch (ParseException e) {
			throw new ConvertException(e, string);
		} catch (NumberFormatException e) {
			throw new ConvertException(e, string);
		}
	}

}
