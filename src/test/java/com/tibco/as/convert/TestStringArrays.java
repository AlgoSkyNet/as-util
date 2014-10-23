package com.tibco.as.convert;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.Tuple;

public class TestStringArrays {

	private ConverterFactory factory = new ConverterFactory();

	@Test
	public void testStringArrayToTuple() throws Exception {
		Collection<Field> fields = getFields();
		IConverter converter = factory.getJavaConverter(fields);
		String[] array = { "P1,000", "2,000", "3,000", "4,000" };
		Tuple tuple = (Tuple) converter.convert(array);
		Assert.assertEquals(1000, tuple.getInt("field1").intValue());
	}

	@Test
	public void testTupleToStringArray() throws Exception {
		Collection<Field> fields = getFields();
		IConverter converter = factory.getTupleConverter(fields, String.class);
		Tuple tuple = Tuple.create();
		tuple.putInt("field1", 1000);
		tuple.putInt("field2", 2000);
		tuple.putInt("field3", 3000);
		tuple.putInt("field4", 4000);
		String[] array = (String[]) converter.convert(tuple);
		Assert.assertEquals("P1,000", array[0]);
	}

	private Collection<Field> getFields() {
		Settings defaults = new Settings();
		defaults.setNumberPattern("#,###");
		Field field1 = new Field();
		field1.setFieldName("field1");
		field1.setFieldType(FieldType.INTEGER);
		field1.setJavaType(String.class);
		field1.getConversion().setNumberPattern("'P'#,###");
		field1.getConversion().setDefaults(defaults);
		Field field2 = new Field();
		field2.setFieldName("field2");
		field2.setJavaType(String.class);
		field2.setFieldType(FieldType.INTEGER);
		field2.getConversion().setDefaults(defaults);
		Field field3 = new Field();
		field3.setFieldName("field3");
		field3.setJavaType(String.class);
		field3.setFieldType(FieldType.INTEGER);
		field3.getConversion().setDefaults(defaults);
		Field field4 = new Field();
		field4.setFieldName("field4");
		field4.setJavaType(String.class);
		field4.setFieldType(FieldType.INTEGER);
		field4.getConversion().setDefaults(defaults);
		return Arrays.asList(field1, field2, field3, field4);
	}
}
