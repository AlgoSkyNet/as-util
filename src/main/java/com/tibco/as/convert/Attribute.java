package com.tibco.as.convert;

import java.util.TimeZone;

import com.tibco.as.convert.ConverterFactory.Blob;

public class Attribute<T> {

	public static final Attribute<Blob> BLOB = new Attribute<Blob>();

	public static final Attribute<String> BOOLEAN = new Attribute<String>();

	public static final Attribute<String> DATE = new Attribute<String>();

	public static final Attribute<TimeZone> TIMEZONE = new Attribute<TimeZone>();

	public static final Attribute<String> DECIMAL = new Attribute<String>();
	
	public static final Attribute<String> INTEGER = new Attribute<String>();

}
