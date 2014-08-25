package com.tibco.as.convert.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class BooleanFormat extends Format {

	private static final long serialVersionUID = -2534441437805237858L;

	private String truePattern;
	private String falsePattern;

	public BooleanFormat(String pattern) {
		String[] patterns = pattern.split("\\|");
		this.truePattern = patterns[0];
		if (patterns.length > 1) {
			this.falsePattern = patterns[1];
		}
	}

	@Override
	public StringBuffer format(Object object, StringBuffer buffer,
			FieldPosition position) {
		if (object != null) {
			if ((object instanceof Boolean)
					&& ((Boolean) object).booleanValue()) {
				buffer.append(truePattern);
			} else {
				buffer.append(falsePattern);
			}
		}
		return buffer;
	}

	@Override
	public Boolean parseObject(String source, ParsePosition position) {
		if (source == null) {
			return null;
		}
		position.setIndex(source.length());
		return truePattern.equalsIgnoreCase(source);
	}

}
