package com.tibco.as.convert;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.accessors.AccessorFactory;
import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.array.ArrayToTupleConverter;
import com.tibco.as.convert.array.TupleToArrayConverter;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;

public class TestStringArrays {

	private ConverterFactory factory = new ConverterFactory();

	@Test
	public void testStringArrayToTuple() throws Exception {
		String spaceName = "space1";
		SpaceDef spaceDef = SpaceDef.create(spaceName);
		Collection<FieldDef> fieldDefs = Arrays.asList(
				FieldDef.create("field1", FieldType.INTEGER),
				FieldDef.create("field2", FieldType.INTEGER),
				FieldDef.create("field3", FieldType.INTEGER),
				FieldDef.create("field4", FieldType.INTEGER));
		spaceDef.getFieldDefs().addAll(fieldDefs);
		Attributes attributes = new Attributes();
		attributes.put(Attribute.INTEGER, "#,###");
		attributes.put(Attribute.INTEGER, "'P'#,###", "field1");
		ITupleAccessor[] accessors = AccessorFactory.create(spaceDef);
		IConverter[] converters = factory.getConverters(attributes,
				String.class, spaceDef);
		ArrayToTupleConverter converter = new ArrayToTupleConverter(accessors,
				converters);
		String[] array = { "P1,000", "2,000", "3,000", "4,000" };
		Tuple tuple = converter.convert(array);
		Assert.assertEquals(1000, tuple.getInt("field1").intValue());
	}

	@Test
	public void testTupleToStringArray() throws Exception {
		SpaceDef spaceDef = SpaceDef.create("space1");
		Collection<FieldDef> fieldDefs = Arrays.asList(
				FieldDef.create("field1", FieldType.INTEGER),
				FieldDef.create("field2", FieldType.INTEGER),
				FieldDef.create("field3", FieldType.INTEGER),
				FieldDef.create("field4", FieldType.INTEGER));
		spaceDef.getFieldDefs().addAll(fieldDefs);
		Attributes attributes = new Attributes();
		attributes.put(Attribute.INTEGER, "#,###");
		attributes.put(Attribute.INTEGER, "'P'#,###", "field1");
		Tuple tuple = Tuple.create();
		tuple.putInt("field1", 1000);
		tuple.putInt("field2", 2000);
		tuple.putInt("field3", 3000);
		tuple.putInt("field4", 4000);
		ITupleAccessor[] accessors = AccessorFactory.create(spaceDef);
		IConverter[] converters = factory.getConverters(attributes, spaceDef,
				String.class);
		TupleToArrayConverter converter = new TupleToArrayConverter(accessors,
				converters);
		Object[] array = (Object[]) converter.convert(tuple);
		Assert.assertEquals("P1,000", array[0]);
	}
}
