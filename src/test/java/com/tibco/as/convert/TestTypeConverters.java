package com.tibco.as.convert;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;

@SuppressWarnings("unchecked")
public class TestTypeConverters {

	private ConverterFactory factory = new ConverterFactory();

	@Test
	public void testStringLongTrimConverter() throws Exception {
		IConverter<String, Long> converter = getConverter(String.class,
				Long.class);
		Assert.assertEquals(new Long(123123L), converter.convert("123123"));
	}

	@Test
	public void testStringLongNoTrimConverter() throws Exception {
		IConverter<String, Long> converter = getConverter(String.class,
				Long.class);
		try {
			converter.convert(" 123123 ");
			Assert.fail("Should have rejected the whitespace");
		} catch (ConvertException e) {
		}
	}

	@Test
	public void testLongStringConverter() throws Exception {
		IConverter<Long, String> converter = getConverter(Long.class,
				String.class);
		Assert.assertEquals("123123", converter.convert(123123L));
	}

	@Test
	public void testStringShortConverter() throws Exception {
		IConverter<String, Short> converter = getConverter(String.class,
				Short.class);
		Assert.assertEquals(new Short((short) 123), converter.convert("123"));
	}

	@Test
	public void testShortStringConverter() throws Exception {
		IConverter<Short, String> converter = getConverter(Short.class,
				String.class);
		Assert.assertEquals("123", converter.convert((short) 123));
	}

	@Test
	public void testDateDateTimeConverter() throws Exception {
		IConverter<Date, DateTime> converter = getConverter(Date.class,
				DateTime.class);
		Calendar cal = Calendar.getInstance();
		Assert.assertEquals(DateTime.create(cal),
				converter.convert(cal.getTime()));
	}

	@Test
	public void testDateTimeStringConverter() throws Exception {
		IConverter<DateTime, String> converter = getConverter(DateTime.class,
				String.class);
		DateTime dateTime = DateTime.create();
		Calendar actual = DatatypeConverter.parseDateTime(converter
				.convert(dateTime));
		Assert.assertEquals(dateTime.getTime().getTimeInMillis(),
				actual.getTimeInMillis());
	}

	@Test
	public void testCalendarStringConverter() throws Exception {
		IConverter<Calendar, DateTime> converter = getConverter(Calendar.class,
				DateTime.class);
		Calendar calendar = Calendar.getInstance();
		Assert.assertEquals(DateTime.create(calendar),
				converter.convert(calendar));
	}

	@Test
	public void testStringBytesConverter() throws Exception {
		IConverter<String, byte[]> converter = getConverter(String.class,
				byte[].class);
		Assert.assertArrayEquals(new byte[] { 1, -1 },
				converter.convert("01FF"));
	}

	@Test
	public void testDateTimeToString() throws Exception {
		String dateString = "2014-04-18T12:34:56.789+01:00";
		Calendar calendar = DatatypeConverter.parseDateTime(dateString);
		// calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		DateTime dateTime = DateTime.create(calendar);
		FieldDef fieldDef = FieldDef.create("field1", FieldType.DATETIME);
		IConverter<DateTime, String> converter = factory.getConverter(
				new Attributes(), fieldDef, String.class);
		Assert.assertEquals(calendar.getTimeInMillis(), DatatypeConverter
				.parseDateTime(converter.convert(dateTime)).getTimeInMillis());
	}

	private <S, T> IConverter<S, T> getConverter(Class<S> from, Class<T> to)
			throws UnsupportedConversionException {
		return factory.getConverter(new Attributes(), from, to);
	}
}
