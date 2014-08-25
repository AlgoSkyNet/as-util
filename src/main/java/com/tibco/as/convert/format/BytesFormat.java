package com.tibco.as.convert.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public abstract class BytesFormat extends Format {

	private static final long serialVersionUID = -4881773911981927769L;

	@Override
	public StringBuffer format(Object object, StringBuffer buffer,
			FieldPosition position) {
		buffer.append(format((byte[]) object));
		return buffer;
	}

	protected abstract String format(byte[] bytes);

	@Override
	public Object parseObject(String string, ParsePosition position) {
		byte[] result = parse(string);
		position.setIndex(string.length());
		return result;
	}

	protected abstract byte[] parse(String string);

}