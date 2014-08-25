package com.tibco.as.convert;

import java.text.MessageFormat;

public class ConvertException extends Exception {

	private static final long serialVersionUID = 317817317968747960L;

	private Object value;

	public ConvertException(Throwable cause, Object value) {
		super(cause);
		this.value = value;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format("Could not convert ''{0}''", value);
	}

}
