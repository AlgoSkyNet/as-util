package com.tibco.as.convert;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.Tuple;

public class TestStringArrays {

	private ConverterFactory factory = new ConverterFactory();

	@Test
	public void testStringArrayToTuple() throws Exception {
		Space space = getSpace();
		space.setDirection(Direction.IMPORT);
		IConverter converter = factory.getArrayConverter(space);
		String[] array = { "P1,000", "2,000", "3,000", "4,000" };
		Tuple tuple = (Tuple) converter.convert(array);
		Assert.assertEquals(1000, tuple.getInt("field1").intValue());
	}

	@Test
	public void testTupleToStringArray() throws Exception {
		Space space = getSpace();
		space.setDirection(Direction.EXPORT);
		Tuple tuple = Tuple.create();
		tuple.putInt("field1", 1000);
		tuple.putInt("field2", 2000);
		tuple.putInt("field3", 3000);
		tuple.putInt("field4", 4000);
		IConverter converter = factory.getArrayConverter(space);
		Object[] array = (Object[]) converter.convert(tuple);
		Assert.assertEquals("P1,000", array[0]);
	}

	private Space getSpace() {
		TestSpace space = new TestSpace();
		space.setSpace("space1");
		TestField field1 = new TestField(space);
		field1.setFieldName("field1");
		field1.setFieldType(FieldType.INTEGER);
		field1.setJavaType(String.class);
		field1.setIntegerFormat("'P'#,###");
		TestField field2 = new TestField(space);
		field2.setFieldName("field2");
		field2.setJavaType(String.class);
		field2.setFieldType(FieldType.INTEGER);
		TestField field3 = new TestField(space);
		field3.setFieldName("field3");
		field3.setJavaType(String.class);
		field3.setFieldType(FieldType.INTEGER);
		TestField field4 = new TestField(space);
		field4.setFieldName("field4");
		field4.setJavaType(String.class);
		field4.setFieldType(FieldType.INTEGER);
		space.getFields().addAll(Arrays.asList(field1, field2, field3, field4));
		space.setIntegerFormat("#,###");
		return space;
	}
}
