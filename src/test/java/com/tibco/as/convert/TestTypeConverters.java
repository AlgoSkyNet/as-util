package com.tibco.as.convert;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef.FieldType;

public class TestTypeConverters {

	private ConverterFactory factory = new ConverterFactory();

	@Test
	public void testStringLongTrimConverter() throws Exception {
		IConverter converter = getConverter(String.class, Long.class);
		Assert.assertEquals(new Long(123123L), converter.convert("123123"));
	}

	@Test
	public void testStringLongNoTrimConverter() throws Exception {
		IConverter converter = getConverter(String.class, Long.class);
		try {
			converter.convert(" 123123 ");
			Assert.fail("Should have rejected the whitespace");
		} catch (ConvertException e) {
		}
	}

	@Test
	public void testLongStringConverter() throws Exception {
		IConverter converter = getConverter(Long.class, String.class);
		Assert.assertEquals("123123", converter.convert(123123L));
	}

	@Test
	public void testStringShortConverter() throws Exception {
		IConverter converter = getConverter(String.class, Short.class);
		Assert.assertEquals(new Short((short) 123), converter.convert("123"));
	}

	@Test
	public void testShortStringConverter() throws Exception {
		IConverter converter = getConverter(Short.class, String.class);
		Assert.assertEquals("123", converter.convert((short) 123));
	}

	@Test
	public void testDateDateTimeConverter() throws Exception {
		IConverter converter = getConverter(Date.class, DateTime.class);
		Calendar cal = Calendar.getInstance();
		Assert.assertEquals(DateTime.create(cal),
				converter.convert(cal.getTime()));
	}

	@Test
	public void testDateTimeStringConverter() throws Exception {
		IConverter converter = getConverter(DateTime.class, String.class);
		DateTime dateTime = DateTime.create();
		Calendar actual = DatatypeConverter.parseDateTime((String) converter
				.convert(dateTime));
		Assert.assertEquals(dateTime.getTime().getTimeInMillis(),
				actual.getTimeInMillis());
	}

	@Test
	public void testCalendarStringConverter() throws Exception {
		IConverter converter = getConverter(Calendar.class, DateTime.class);
		Calendar calendar = Calendar.getInstance();
		Assert.assertEquals(DateTime.create(calendar),
				converter.convert(calendar));
	}

	@Test
	public void testStringBytesConverter() throws Exception {
		IConverter converter = getConverter(String.class, byte[].class);
		Assert.assertArrayEquals(new byte[] { 1, -1 },
				(byte[]) converter.convert("01FF"));
	}

	@Test
	public void testDateTimeToString() throws Exception {
		String dateString = "2014-04-18T12:34:56.789+01:00";
		Calendar calendar = DatatypeConverter.parseDateTime(dateString);
		// calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		DateTime dateTime = DateTime.create(calendar);
		Space space = new Space();
		Field field = space.addField();
		field.setFieldName("field1");
		field.setFieldType(FieldType.DATETIME);
		field.setJavaType(String.class);
		IConverter converter = factory.getFieldConverter(field);
		Assert.assertEquals(calendar.getTimeInMillis(), DatatypeConverter
				.parseDateTime((String) converter.convert(dateTime))
				.getTimeInMillis());
	}

	private IConverter getConverter(Class<?> from, Class<?> to)
			throws UnsupportedConversionException {
		return factory.getConverter(new Space().addField(), from, to);
	}
}
