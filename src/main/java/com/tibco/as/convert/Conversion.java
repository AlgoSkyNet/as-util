package com.tibco.as.convert;

@SuppressWarnings("rawtypes")
public class Conversion {

	private Class from;

	private Class to;

	private Class converter;
	
	public Conversion(Class from, Class to, Class converter) {
		this.from = from;
		this.to = to;
		this.converter = converter;
	}

	public Class getFrom() {
		return from;
	}

	public void setFrom(Class from) {
		this.from = from;
	}

	public Class getTo() {
		return to;
	}

	public void setTo(Class to) {
		this.to = to;
	}

	public Class getConverter() {
		return converter;
	}
	
	public void setConverter(Class converter) {
		this.converter = converter;
	}

}
