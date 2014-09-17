package com.tibco.as.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.util.Field;
import com.tibco.as.util.FieldFormat;

public class TestFieldFormat {

	@Test
	public void testFormatObject() {
		FieldFormat format = new FieldFormat();
		Field field = new Field();
		field.setName("field-1");
		assertEquals("name 'field-1'", format.format(field));
		field.setType(FieldType.DATETIME);
		assertEquals("name 'field-1' type 'datetime'", format.format(field));
		field.setEncrypted(false);
		assertEquals("name 'field-1' type 'datetime'", format.format(field));
		field.setEncrypted(true);
		assertEquals("name 'field-1' type 'datetime' encrypted true",
				format.format(field));
		field.setKey(true);
		assertEquals("name 'field-1' type 'datetime' encrypted true key true",
				format.format(field));
		field.setNullable(true);
		assertEquals(
				"name 'field-1' type 'datetime' nullable true encrypted true key true",
				format.format(field));
	}

	@Test
	public void testParseObjectString() throws ParseException {
		FieldFormat format = new FieldFormat();
		Field field = format.parseObject("field-1");
		assertEquals("field-1", field.getName());
		field = format.parseObject("name 'field-1'");
		assertEquals("field-1", field.getName());
		assertEquals(null, field.getType());
		assertEquals(false, field.isKey());
		assertEquals(false, field.isEncrypted());
		assertEquals(false, field.isNullable());
		field = format.parseObject("name 'field-1' type 'datetime'");
		assertEquals("field-1", field.getName());
		assertEquals(FieldType.DATETIME, field.getType());
		assertEquals(false, field.isKey());
		assertEquals(false, field.isEncrypted());
		assertEquals(false, field.isNullable());
		field = format.parseObject("name 'field-1' type 'datetime' key true");
		assertEquals("field-1", field.getName());
		assertEquals(FieldType.DATETIME, field.getType());
		assertEquals(true, field.isKey());
		assertEquals(false, field.isEncrypted());
		assertEquals(false, field.isNullable());
		field = format
				.parseObject("name 'field-1' type 'datetime' key true encrypted true nullable true");
		assertEquals("field-1", field.getName());
		assertEquals(FieldType.DATETIME, field.getType());
		assertEquals(true, field.isKey());
		assertEquals(true, field.isEncrypted());
		assertEquals(true, field.isNullable());
	}

}
