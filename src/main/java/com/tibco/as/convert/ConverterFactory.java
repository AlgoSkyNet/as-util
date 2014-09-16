package com.tibco.as.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import com.tibco.as.convert.converters.BigIntegerToBytes;
import com.tibco.as.convert.converters.BooleanToBytes;
import com.tibco.as.convert.converters.BooleanToNumber;
import com.tibco.as.convert.converters.BooleanToString;
import com.tibco.as.convert.converters.BytesToBigInteger;
import com.tibco.as.convert.converters.BytesToBoolean;
import com.tibco.as.convert.converters.BytesToCharacter;
import com.tibco.as.convert.converters.BytesToDouble;
import com.tibco.as.convert.converters.BytesToFloat;
import com.tibco.as.convert.converters.BytesToInteger;
import com.tibco.as.convert.converters.BytesToLong;
import com.tibco.as.convert.converters.BytesToShort;
import com.tibco.as.convert.converters.BytesToString;
import com.tibco.as.convert.converters.CalendarToDate;
import com.tibco.as.convert.converters.CalendarToDateTime;
import com.tibco.as.convert.converters.CharacterToBytes;
import com.tibco.as.convert.converters.CharacterToNumber;
import com.tibco.as.convert.converters.CharacterToString;
import com.tibco.as.convert.converters.DateTimeToCalendar;
import com.tibco.as.convert.converters.DateTimeToDate;
import com.tibco.as.convert.converters.DateToCalendar;
import com.tibco.as.convert.converters.DateToDateTime;
import com.tibco.as.convert.converters.DateToLong;
import com.tibco.as.convert.converters.DateToString;
import com.tibco.as.convert.converters.DoubleToBigDecimal;
import com.tibco.as.convert.converters.DoubleToBytes;
import com.tibco.as.convert.converters.DoubleToString;
import com.tibco.as.convert.converters.FloatToBytes;
import com.tibco.as.convert.converters.FloatToString;
import com.tibco.as.convert.converters.ISO8601ToString;
import com.tibco.as.convert.converters.Idem;
import com.tibco.as.convert.converters.IntegerToBytes;
import com.tibco.as.convert.converters.IntegerToString;
import com.tibco.as.convert.converters.LongToBigDecimal;
import com.tibco.as.convert.converters.LongToBigInteger;
import com.tibco.as.convert.converters.LongToBytes;
import com.tibco.as.convert.converters.LongToDate;
import com.tibco.as.convert.converters.LongToString;
import com.tibco.as.convert.converters.NumberToBoolean;
import com.tibco.as.convert.converters.NumberToCharacter;
import com.tibco.as.convert.converters.NumberToDouble;
import com.tibco.as.convert.converters.NumberToFloat;
import com.tibco.as.convert.converters.NumberToInteger;
import com.tibco.as.convert.converters.NumberToLong;
import com.tibco.as.convert.converters.NumberToShort;
import com.tibco.as.convert.converters.ShortToBytes;
import com.tibco.as.convert.converters.ShortToString;
import com.tibco.as.convert.converters.StringToBigDecimal;
import com.tibco.as.convert.converters.StringToBoolean;
import com.tibco.as.convert.converters.StringToBytes;
import com.tibco.as.convert.converters.StringToCharacter;
import com.tibco.as.convert.converters.StringToDate;
import com.tibco.as.convert.converters.StringToDouble;
import com.tibco.as.convert.converters.StringToFloat;
import com.tibco.as.convert.converters.StringToISO8601;
import com.tibco.as.convert.converters.StringToInteger;
import com.tibco.as.convert.converters.StringToLong;
import com.tibco.as.convert.converters.StringToShort;
import com.tibco.as.convert.converters.StringToURL;
import com.tibco.as.convert.converters.URLToString;
import com.tibco.as.convert.format.Base64Format;
import com.tibco.as.convert.format.BooleanFormat;
import com.tibco.as.convert.format.BytesFormat;
import com.tibco.as.convert.format.HexFormat;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.SpaceDef;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConverterFactory {

	public enum Blob {
		BASE64, HEX
	}

	public final static String DEFAULT_PATTERN_BOOLEAN = "true|false";

	public final static TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT");

	private Attributes attributes = new Attributes();

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public void setBlobFormat(Blob blobFormat) {
		attributes.put(Attribute.BLOB, blobFormat);
	}

	public void setDateFormat(String dateFormat) {
		attributes.put(Attribute.DATE, dateFormat);
	}

	public void setTimeZone(TimeZone timeZone) {
		attributes.put(Attribute.TIMEZONE, timeZone);
	}

	public static BytesFormat getBlobFormat(Attributes attributes) {
		Blob attribute = attributes.get(Attribute.BLOB);
		if (attribute == Blob.BASE64) {
			return new Base64Format();
		}
		return new HexFormat();
	}

	public static BooleanFormat getBooleanFormat(Attributes attributes) {
		return new BooleanFormat(getBooleanPattern(attributes));
	}

	public static String getBooleanPattern(Attributes attributes) {
		String pattern = attributes.get(Attribute.BOOLEAN);
		if (pattern == null) {
			return DEFAULT_PATTERN_BOOLEAN;
		}
		return pattern;
	}

	private static Logger logger = Logger.getLogger(ConverterFactory.class
			.getName());

	private Map<Class, Map<Class, Class<? extends IConverter>>> converters = new LinkedHashMap<Class, Map<Class, Class<? extends IConverter>>>();

	public ConverterFactory() {
		register(BigInteger.class, byte[].class, BigIntegerToBytes.class);
		register(Boolean.class, byte[].class, BooleanToBytes.class);
		register(Boolean.class, Number.class, BooleanToNumber.class);
		register(Boolean.class, String.class, BooleanToString.class);
		register(byte[].class, BigInteger.class, BytesToBigInteger.class);
		register(byte[].class, Boolean.class, BytesToBoolean.class);
		register(byte[].class, Character.class, BytesToCharacter.class);
		register(byte[].class, Double.class, BytesToDouble.class);
		register(byte[].class, Float.class, BytesToFloat.class);
		register(byte[].class, Integer.class, BytesToInteger.class);
		register(byte[].class, Long.class, BytesToLong.class);
		register(byte[].class, Short.class, BytesToShort.class);
		register(byte[].class, String.class, BytesToString.class);
		register(Calendar.class, Date.class, CalendarToDate.class);
		register(Calendar.class, DateTime.class, CalendarToDateTime.class);
		register(Character.class, byte[].class, CharacterToBytes.class);
		register(Character.class, Number.class, CharacterToNumber.class);
		register(Character.class, String.class, CharacterToString.class);
		register(Date.class, Calendar.class, DateToCalendar.class);
		register(Date.class, DateTime.class, DateToDateTime.class);
		register(Date.class, Long.class, DateToLong.class);
		register(DateTime.class, Calendar.class, DateTimeToCalendar.class);
		register(DateTime.class, Date.class, DateTimeToDate.class);
		register(Double.class, BigDecimal.class, DoubleToBigDecimal.class);
		register(Double.class, byte[].class, DoubleToBytes.class);
		register(Double.class, String.class, DoubleToString.class);
		register(Float.class, byte[].class, FloatToBytes.class);
		register(Float.class, String.class, FloatToString.class);
		register(Integer.class, byte[].class, IntegerToBytes.class);
		register(Integer.class, String.class, IntegerToString.class);
		register(Long.class, BigInteger.class, LongToBigInteger.class);
		register(Long.class, byte[].class, LongToBytes.class);
		register(Long.class, Date.class, LongToDate.class);
		register(Long.class, String.class, LongToString.class);
		register(Long.class, BigDecimal.class, LongToBigDecimal.class);
		register(Number.class, Boolean.class, NumberToBoolean.class);
		register(Number.class, Character.class, NumberToCharacter.class);
		register(Number.class, Double.class, NumberToDouble.class);
		register(Number.class, Float.class, NumberToFloat.class);
		register(Number.class, Integer.class, NumberToInteger.class);
		register(Number.class, Long.class, NumberToLong.class);
		register(Number.class, Short.class, NumberToShort.class);
		register(Short.class, byte[].class, ShortToBytes.class);
		register(Short.class, String.class, ShortToString.class);
		register(String.class, BigDecimal.class, StringToBigDecimal.class);
		register(String.class, Boolean.class, StringToBoolean.class);
		register(String.class, byte[].class, StringToBytes.class);
		register(String.class, Character.class, StringToCharacter.class);
		register(String.class, Double.class, StringToDouble.class);
		register(String.class, Float.class, StringToFloat.class);
		register(String.class, Integer.class, StringToInteger.class);
		register(String.class, Long.class, StringToLong.class);
		register(String.class, Short.class, StringToShort.class);
		register(String.class, URL.class, StringToURL.class);
		register(URL.class, String.class, URLToString.class);
	}

	private void register(Class from, Class to,
			Class<? extends IConverter> converter) {
		if (!converters.containsKey(from)) {
			converters.put(from,
					new LinkedHashMap<Class, Class<? extends IConverter>>());
		}
		Map<Class, Class<? extends IConverter>> map = converters.get(from);
		if (map.containsKey(to)) {
			logger.warning(MessageFormat.format(
					"Duplicate converters: {0} - {1}", map.get(to).getName(),
					converter.getName()));
		}
		map.put(to, converter);

	}

	private IConverter newConverterInstance(Class clazz, Attributes attributes)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor[] constructors = clazz.getConstructors();
		for (Constructor constructor : constructors) {
			Class[] parameterTypes = constructor.getParameterTypes();
			if (parameterTypes.length == 0) {
				return (IConverter) constructor.newInstance();
			}
			return (IConverter) constructor.newInstance(attributes);
		}
		throw new InstantiationException("Could not instanciate type "
				+ clazz.getName());
	}

	public IConverter getConverter(Attributes attributes, Class from,
			FieldDef to) throws UnsupportedConversionException {
		Attributes nameAttributes = attributes.getAttributes(to.getName());
		return getConverter(nameAttributes, from, getType(to));
	}

	public IConverter getConverter(Attributes attributes, FieldDef from,
			Class to) throws UnsupportedConversionException {
		Attributes nameAttributes = attributes.getAttributes(from.getName());
		return getConverter(nameAttributes, getType(from), to);
	}

	public Class getType(FieldDef fieldDef) {
		return getType(fieldDef.getType());
	}

	public Class getType(FieldType fieldType) {
		switch (fieldType) {
		case BLOB:
			return byte[].class;
		case BOOLEAN:
			return Boolean.class;
		case CHAR:
			return Character.class;
		case DATETIME:
			return DateTime.class;
		case DOUBLE:
			return Double.class;
		case FLOAT:
			return Float.class;
		case INTEGER:
			return Integer.class;
		case LONG:
			return Long.class;
		case SHORT:
			return Short.class;
		default:
			return String.class;
		}
	}

	public FieldType getFieldType(Class clazz) {
		if (byte[].class.isAssignableFrom(clazz)) {
			return FieldType.BLOB;
		}
		if (Boolean.class.isAssignableFrom(clazz)) {
			return FieldType.BOOLEAN;
		}
		if (Character.class.isAssignableFrom(clazz)) {
			return FieldType.CHAR;
		}
		if (DateTime.class.isAssignableFrom(clazz)) {
			return FieldType.DATETIME;
		}
		if (Calendar.class.isAssignableFrom(clazz)) {
			return FieldType.DATETIME;
		}
		if (Date.class.isAssignableFrom(clazz)) {
			return FieldType.DATETIME;
		}
		if (Double.class.isAssignableFrom(clazz)) {
			return FieldType.DOUBLE;
		}
		if (Float.class.isAssignableFrom(clazz)) {
			return FieldType.FLOAT;
		}
		if (Integer.class.isAssignableFrom(clazz)) {
			return FieldType.INTEGER;
		}
		if (Long.class.isAssignableFrom(clazz)) {
			return FieldType.LONG;
		}
		if (Short.class.isAssignableFrom(clazz)) {
			return FieldType.SHORT;
		}
		return FieldType.STRING;
	}

	public IConverter getConverter(Class from, FieldDef to)
			throws UnsupportedConversionException {
		return getConverter(attributes, from, to);
	}

	public IConverter getConverter(FieldDef from, Class to)
			throws UnsupportedConversionException {
		return getConverter(attributes, from, to);
	}

	public IConverter getConverter(Class from, Class to)
			throws UnsupportedConversionException {
		return getConverter(attributes, from, to);
	}

	public IConverter getConverter(Attributes attributes, Class from, Class to)
			throws UnsupportedConversionException {
		if (from.isAssignableFrom(to)) {
			return new Idem();
		}
		for (Class fromCandidate : converters.keySet()) {
			Map<Class, Class<? extends IConverter>> candidates = converters
					.get(fromCandidate);
			for (Class toCandidate : candidates.keySet()) {
				if (matches(from, to, fromCandidate, toCandidate)) {
					try {
						return newConverterInstance(
								candidates.get(toCandidate), attributes);
					} catch (Exception e) {
						throw new UnsupportedConversionException(from, to, e);
					}
				}
			}
		}
		if (Date.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (attributes.get(Attribute.DATE) == null) {
					return new ChainedConverter<Date, Calendar, String>(
							new DateToCalendar(), new ISO8601ToString());
				}
				return new DateToString(getDateFormat(attributes));
			}
		}
		if (Calendar.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (attributes.get(Attribute.DATE) == null) {
					return new ISO8601ToString();
				}
				return new ChainedConverter<Calendar, Date, String>(
						new CalendarToDate(), new DateToString(
								getDateFormat(attributes)));
			}
		}
		if (Date.class.isAssignableFrom(to)) {
			if (String.class.isAssignableFrom(from)) {
				if (attributes.get(Attribute.DATE) == null) {
					return new ChainedConverter<String, Calendar, Date>(
							new StringToISO8601(), new CalendarToDate());
				}
				return new StringToDate(getDateFormat(attributes));
			}
		}
		if (Calendar.class.isAssignableFrom(to)) {
			if (String.class.isAssignableFrom(from)) {
				if (attributes.get(Attribute.DATE) == null) {
					return new StringToISO8601();
				}
				return new ChainedConverter<String, Date, Calendar>(
						new StringToDate(getDateFormat(attributes)),
						new DateToCalendar());
			}
		}
		if (DateTime.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				return new ChainedConverter<DateTime, Calendar, String>(
						new DateTimeToCalendar(), getConverter(attributes,
								Calendar.class, String.class));
			}
			if (Number.class.isAssignableFrom(to)) {
				return new ChainedConverter<DateTime, Long, Number>(
						new ChainedConverter<DateTime, Date, Long>(
								new DateTimeToDate(), new DateToLong()),
						getConverter(attributes, Long.class, to));
			}
		}
		if (DateTime.class.isAssignableFrom(to)) {
			if (String.class.isAssignableFrom(from)) {
				return new ChainedConverter<String, Calendar, DateTime>(
						getConverter(attributes, String.class, Calendar.class),
						new CalendarToDateTime());
			}
			if (Number.class.isAssignableFrom(from)) {
				return new ChainedConverter<Number, Long, DateTime>(
						new NumberToLong(),
						new ChainedConverter<Long, Date, DateTime>(
								new LongToDate(), new DateToDateTime()));
			}
		}
		throw new UnsupportedConversionException(from, to);
	}

	private DateFormat getDateFormat(Attributes attributes) {
		return getDateFormat(getDatePattern(attributes),
				getTimeZone(attributes));
	}

	private String getDatePattern(Attributes attributes) {
		return attributes.get(Attribute.DATE);
	}

	private TimeZone getTimeZone(Attributes attributes) {
		return attributes.get(Attribute.TIMEZONE);
	}

	private boolean matches(Class from, Class to, Class candidateFrom,
			Class candidateTo) {
		return candidateFrom.isAssignableFrom(from)
				&& candidateTo.isAssignableFrom(to);
	}

	public static Format getNumberFormat(String pattern, Format defaultFormat) {
		if (pattern == null) {
			return defaultFormat;
		}
		return new DecimalFormat(pattern);
	}

	public IConverter[] getConverters(Class[] types, FieldDef[] fieldDefs)
			throws UnsupportedConversionException {
		return getConverters(attributes, types, fieldDefs);
	}

	public IConverter[] getConverters(Attributes attributes, Class[] types,
			FieldDef[] fieldDefs) throws UnsupportedConversionException {
		IConverter[] converters = new IConverter[fieldDefs.length];
		for (int index = 0; index < fieldDefs.length; index++) {
			FieldDef fieldDef = fieldDefs[index];
			if (fieldDef == null) {
				continue;
			}
			Class type = index < types.length ? types[index] : types[0];
			converters[index] = getConverter(attributes, type, fieldDef);
		}
		return converters;
	}

	public IConverter[] getConverters(Attributes attributes,
			FieldDef[] fieldDefs, Class type)
			throws UnsupportedConversionException {
		return getConverters(attributes, fieldDefs, new Class[] { type });
	}

	public IConverter[] getConverters(Attributes attributes,
			FieldDef[] fieldDefs, Class[] types)
			throws UnsupportedConversionException {
		IConverter[] converters = new IConverter[fieldDefs.length];
		for (int index = 0; index < fieldDefs.length; index++) {
			FieldDef fieldDef = fieldDefs[index];
			if (fieldDef == null) {
				continue;
			}
			Class type = index < types.length ? types[index] : types[0];
			converters[index] = getConverter(attributes, fieldDef, type);
		}
		return converters;
	}

	public IConverter[] getConverters(Class type, FieldDef[] fieldDefs)
			throws UnsupportedConversionException {
		return getConverters(attributes, type, fieldDefs);
	}

	public IConverter[] getConverters(Attributes attributes, Class type,
			FieldDef[] fieldDefs) throws UnsupportedConversionException {
		return getConverters(attributes, new Class[] { type }, fieldDefs);
	}

	public IConverter[] getConverters(Attributes attributes, Class type,
			SpaceDef spaceDef) throws UnsupportedConversionException {
		return getConverters(attributes, type, getFieldDefs(spaceDef));
	}

	private FieldDef[] getFieldDefs(SpaceDef spaceDef) {
		return spaceDef.getFieldDefs().toArray(
				new FieldDef[spaceDef.getFieldDefs().size()]);
	}

	public IConverter[] getConverters(Attributes attributes, SpaceDef spaceDef,
			Class type) throws UnsupportedConversionException {
		return getConverters(attributes, getFieldDefs(spaceDef), type);
	}

	public IConverter getConverter(FieldDef fieldDef)
			throws UnsupportedConversionException {
		return getConverter(fieldDef, getType(fieldDef));
	}

	public static DateFormat getDateFormat(String pattern, TimeZone timeZone) {
		DateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(timeZone == null ? DEFAULT_TIMEZONE : timeZone);
		return format;
	}

}