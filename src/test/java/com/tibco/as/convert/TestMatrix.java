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
		TestSpace importSpace = new TestSpace();
		importSpace.setDirection(Direction.IMPORT);
		TestSpace exportSpace = new TestSpace();
		importSpace.setDirection(Direction.EXPORT);
		Class[] classes = { Double.class, Float.class, Integer.class,
				Long.class, Short.class, String.class, Date.class,
				Calendar.class, byte[].class };
		FieldType[] fieldTypes = { FieldType.BLOB, FieldType.BOOLEAN,
				FieldType.CHAR, FieldType.DATETIME, FieldType.DOUBLE,
				FieldType.FLOAT, FieldType.INTEGER, FieldType.LONG,
				FieldType.SHORT, FieldType.STRING };
		for (Class clazz : classes) {
			for (FieldType fieldType : fieldTypes) {
				TestField importField = new TestField(importSpace);
				importField.setFieldType(fieldType);
				importField.setJavaType(clazz);
				try {
					factory.getConverter(importField);
				} catch (UnsupportedConversionException e) {
					System.out
							.println(e.getFromType() + " -> " + e.getToType());
				}
				TestField exportField = new TestField(exportSpace);
				exportField.setFieldType(fieldType);
				exportField.setJavaType(clazz);
				try {
					factory.getConverter(exportField);
				} catch (UnsupportedConversionException e) {
					System.out
							.println(e.getFromType() + " -> " + e.getToType());
				}
			}
		}
	}

}
