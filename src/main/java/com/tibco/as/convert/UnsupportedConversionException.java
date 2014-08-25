package com.tibco.as.convert;

@SuppressWarnings("rawtypes")
public class UnsupportedConversionException extends Exception {

	private static final long serialVersionUID = -7286085642808233518L;

	private Class fromType;
	private Class toType;

	public UnsupportedConversionException(Class fromType, Class toType) {
		this.fromType = fromType;
		this.toType = toType;
	}

	public UnsupportedConversionException(Class from, Class to, Throwable cause) {
		super(cause);
		this.fromType = from;
		this.toType = to;
	}

	public Class getFromType() {
		return fromType;
	}

	public void setFromType(Class fromType) {
		this.fromType = fromType;
	}

	public Class getToType() {
		return toType;
	}

	public void setToType(Class toType) {
		this.toType = toType;
	}

}
