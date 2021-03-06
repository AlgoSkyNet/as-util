package com.tibco.as.util.convert;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef.FieldType;

public class TestConverters {

	@Test
	public void testStringLongTrimConverter() throws Exception {
		IConverter converter = getConverter(String.class, Long.class);
		Assert.assertEquals(new Long(123123L), converter.convert("123123"));
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
		Assert.assertEquals(
				calendar.getTimeInMillis(),
				DatatypeConverter.parseDateTime(
						(String) getConverter(FieldType.DATETIME, String.class)
								.convert(dateTime)).getTimeInMillis());
	}

	private IConverter getConverter(FieldType fieldType, Class<?> javaType) {
		return new ConverterFactory().getConverter(
				ConverterFactory.getJavaType(fieldType), javaType);
	}

	private IConverter getConverter(Class<?> from, Class<?> to) {
		return new ConverterFactory().getConverter(from, to);
	}

	@Test
	public void testToString() throws Exception {
		ConversionConfig defaults = new ConversionConfig();
		defaults.setNumberPattern("#,###");
		ConversionConfig settings = new ConversionConfig();
		settings.setNumberPattern("'P'#,###");
		String string1 = "P1,000";
		Integer int1 = 1000;
		ConverterFactory factory = new ConverterFactory();
		factory.setConfig(settings);
		Assert.assertEquals(string1,
				factory.getConverter(Integer.class, String.class).convert(int1));
		Assert.assertEquals(
				int1,
				factory.getConverter(String.class, Integer.class).convert(
						string1));
		String string2 = "2,000";
		Integer int2 = 2000;
		factory.setConfig(defaults);
		Assert.assertEquals(string2,
				factory.getConverter(Integer.class, String.class).convert(int2));
		Assert.assertEquals(
				int2,
				factory.getConverter(String.class, Integer.class).convert(
						string2));
	}

}
