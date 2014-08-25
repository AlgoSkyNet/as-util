package com.tibco.as.convert.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public abstract class SimpleFormat extends Format {

	private static final long serialVersionUID = -1204115436946680182L;

	@Override
	public StringBuffer format(Object obj, StringBuffer buffer,
			FieldPosition pos) {
		return buffer.append(obj);
	}

	@Override
	public Object parseObject(String source, ParsePosition position) {
		if (source == null) {
			return null;
		}
		Object object = parse(source);
		position.setIndex(source.length());
		return object;
	}

	protected abstract Object parse(String source);

}
