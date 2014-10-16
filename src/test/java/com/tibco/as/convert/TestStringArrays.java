package com.tibco.as.convert;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.Tuple;

public class TestStringArrays {

	private ConverterFactory factory = new ConverterFactory();

	@Test
	public void testStringArrayToTuple() throws Exception {
		Space space = getSpace();
		IConverter converter = factory.getJavaConverter(space);
		String[] array = { "P1,000", "2,000", "3,000", "4,000" };
		Tuple tuple = (Tuple) converter.convert(array);
		Assert.assertEquals(1000, tuple.getInt("field1").intValue());
	}

	@Test
	public void testTupleToStringArray() throws Exception {
		Space space = getSpace();
		Tuple tuple = Tuple.create();
		tuple.putInt("field1", 1000);
		tuple.putInt("field2", 2000);
		tuple.putInt("field3", 3000);
		tuple.putInt("field4", 4000);
		IConverter converter = factory.getTupleConverter(space);
		Object[] array = (Object[]) converter.convert(tuple);
		Assert.assertEquals("P1,000", array[0]);
	}

	private Space getSpace() {
		Space space = new Space();
		space.setSpace("space1");
		Field field1 = space.addField();
		field1.setFieldName("field1");
		field1.setFieldType(FieldType.INTEGER);
		field1.setJavaType(String.class);
		field1.setIntegerFormat("'P'#,###");
		Field field2 = space.addField();
		field2.setFieldName("field2");
		field2.setJavaType(String.class);
		field2.setFieldType(FieldType.INTEGER);
		Field field3 = space.addField();
		field3.setFieldName("field3");
		field3.setJavaType(String.class);
		field3.setFieldType(FieldType.INTEGER);
		Field field4 = space.addField();
		field4.setFieldName("field4");
		field4.setJavaType(String.class);
		field4.setFieldType(FieldType.INTEGER);
		space.setIntegerFormat("#,###");
		return space;
	}
}
