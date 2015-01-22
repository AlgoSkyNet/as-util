package com.tibco.as.util.convert.impl;

public abstract class AbstractStringParser<T> extends
		AbstractConverter<String, T> {

	@Override
	protected T doConvert(String source) {
		if (source.isEmpty()) {
			return null;
		}
		return parse(source);

	}

	protected abstract T parse(String string);

}
