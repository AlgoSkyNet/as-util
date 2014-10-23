package com.tibco.as.convert;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.tibco.as.space.FieldDef.FieldType;

@SuppressWarnings("rawtypes")
public class TestMatrix {

	ConverterFactory factory = new ConverterFactory();

	@Test
	public void testMatrix() {
		Class[] classes = { Double.class, Float.class, Integer.class,
				Long.class, Short.class, String.class, Date.class,
				Calendar.class, byte[].class };
		FieldType[] fieldTypes = { FieldType.BLOB, FieldType.BOOLEAN,
				FieldType.CHAR, FieldType.DATETIME, FieldType.DOUBLE,
				FieldType.FLOAT, FieldType.INTEGER, FieldType.LONG,
				FieldType.SHORT, FieldType.STRING };
		for (Class clazz : classes) {
			for (FieldType fieldType : fieldTypes) {
				Field field = new Field();
				field.setFieldType(fieldType);
				field.setJavaType(clazz);
				try {
					factory.getJavaConverter(field);
				} catch (UnsupportedConversionException e) {
					System.out
							.println(e.getFromType() + " -> " + e.getToType());
				}
				try {
					factory.getFieldConverter(field);
				} catch (UnsupportedConversionException e) {
					System.out
							.println(e.getFromType() + " -> " + e.getToType());
				}
			}
		}
	}

}
