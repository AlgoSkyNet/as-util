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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.tibco.as.convert.accessors.BlobAccessor;
import com.tibco.as.convert.accessors.BooleanAccessor;
import com.tibco.as.convert.accessors.CharacterAccessor;
import com.tibco.as.convert.accessors.DateTimeAccessor;
import com.tibco.as.convert.accessors.DoubleAccessor;
import com.tibco.as.convert.accessors.FloatAccessor;
import com.tibco.as.convert.accessors.ITupleAccessor;
import com.tibco.as.convert.accessors.IntegerAccessor;
import com.tibco.as.convert.accessors.LongAccessor;
import com.tibco.as.convert.accessors.ShortAccessor;
import com.tibco.as.convert.accessors.StringAccessor;
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
import com.tibco.as.convert.converters.DateTimeToSQLDate;
import com.tibco.as.convert.converters.DateTimeToSQLTime;
import com.tibco.as.convert.converters.DateTimeToSQLTimestamp;
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
import com.tibco.as.log.LogFactory;
import com.tibco.as.space.DateTime;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConverterFactory {

	private static Logger logger = LogFactory.getLog(ConverterFactory.class);
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
		register(DateTime.class, java.sql.Date.class, DateTimeToSQLDate.class);
		register(DateTime.class, java.sql.Time.class, DateTimeToSQLTime.class);
		register(DateTime.class, java.sql.Timestamp.class,
				DateTimeToSQLTimestamp.class);
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

	private IConverter newConverterInstance(Class clazz, Field field)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor[] constructors = clazz.getConstructors();
		for (Constructor constructor : constructors) {
			Class[] parameterTypes = constructor.getParameterTypes();
			if (parameterTypes.length == 0) {
				return (IConverter) constructor.newInstance();
			}
			return (IConverter) constructor.newInstance(field);
		}
		throw new InstantiationException("Could not instanciate type "
				+ clazz.getName());
	}

	public IConverter getConverter(Field field)
			throws UnsupportedConversionException {
		Class<?> from = getFromType(field);
		Class<?> to = getToType(field);
		return getConverter(field, from, to);
	}

	public IConverter getConverter(Field field, Class<?> from, Class<?> to)
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
								candidates.get(toCandidate), field);
					} catch (Exception e) {
						throw new UnsupportedConversionException(from, to, e);
					}
				}
			}
		}
		if (Date.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (field.getDateFormat() == null) {
					return new ChainedConverter(new DateToCalendar(),
							new ISO8601ToString());
				}
				return new DateToString(getDateFormat(field));
			}
		}
		if (Calendar.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (field.getDateFormat() == null) {
					return new ISO8601ToString();
				}
				return new ChainedConverter(new CalendarToDate(),
						new DateToString(getDateFormat(field)));
			}
		}
		if (Date.class.isAssignableFrom(to)) {
			if (String.class.isAssignableFrom(from)) {
				if (field.getDateFormat() == null) {
					return new ChainedConverter(new StringToISO8601(),
							new CalendarToDate());
				}
				return new StringToDate(getDateFormat(field));
			}
		}
		if (Calendar.class.isAssignableFrom(to)) {
			if (String.class.isAssignableFrom(from)) {
				if (field.getDateFormat() == null) {
					return new StringToISO8601();
				}
				return new ChainedConverter(new StringToDate(
						getDateFormat(field)), new DateToCalendar());
			}
		}
		if (DateTime.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				return new ChainedConverter(new DateTimeToCalendar(),
						getConverter(field, Calendar.class, String.class));
			}
			if (Number.class.isAssignableFrom(to)) {
				return new ChainedConverter(new ChainedConverter(
						new DateTimeToDate(), new DateToLong()), getConverter(
						field, Long.class, to));
			}
		}
		if (DateTime.class.isAssignableFrom(to)) {
			if (String.class.isAssignableFrom(from)) {
				return new ChainedConverter(getConverter(field, String.class,
						Calendar.class), new CalendarToDateTime());
			}
			if (Number.class.isAssignableFrom(from)) {
				return new ChainedConverter(new NumberToLong(),
						new ChainedConverter(new LongToDate(),
								new DateToDateTime()));
			}
		}
		if (BigDecimal.class.isAssignableFrom(to)) {
			if (Number.class.isAssignableFrom(from)) {
				return new ChainedConverter(new NumberToLong(),
						new LongToBigDecimal());
			}
		}
		if (BigInteger.class.isAssignableFrom(to)) {
			if (Number.class.isAssignableFrom(from)) {
				return new ChainedConverter(new NumberToLong(),
						new LongToBigInteger());
			}
		}
		throw new UnsupportedConversionException(from, to);
	}

	private DateFormat getDateFormat(Field field) {
		DateFormat format = new SimpleDateFormat(field.getDateFormat());
		format.setTimeZone(field.getTimeZone());
		return format;
	}

	private Class<?> getToType(Field field) {
		if (field.isImport()) {
			return Field.getJavaType(field.getFieldType());
		}
		return field.getJavaType();
	}

	private Class<?> getFromType(Field field) {
		if (field.isImport()) {
			return field.getJavaType();
		}
		return Field.getJavaType(field.getFieldType());
	}

	private boolean matches(Class from, Class to, Class candidateFrom,
			Class candidateTo) {
		return candidateFrom.isAssignableFrom(from)
				&& candidateTo.isAssignableFrom(to);
	}

	public static BytesFormat getBlobFormat(Field field) {
		if (field.getBlobFormat() == Blob.BASE64) {
			return new Base64Format();
		}
		return new HexFormat();
	}

	public static BooleanFormat getBooleanFormat(Field field) {
		return new BooleanFormat(field.getBooleanFormat());
	}

	public static Format getNumberFormat(String pattern, Format defaultFormat) {
		if (pattern == null) {
			return defaultFormat;
		}
		return new DecimalFormat(pattern);
	}

	public Collection<IConverter> getConverters(Space space)
			throws UnsupportedConversionException {
		Collection<IConverter> converters = new ArrayList<IConverter>();
		for (Field field : space.getFields()) {
			converters.add(getConverter(field));
		}
		return converters;
	}

	public IConverter getArrayConverter(Space space)
			throws UnsupportedConversionException {
		Collection<ITupleAccessor> accessors = getAccessors(space);
		Collection<IConverter> converters = getConverters(space);
		if (space.isImport()) {
			return new ArrayTupleConverter(accessors, converters);
		}
		return new TupleArrayConverter(accessors, converters);
	}

	private ITupleAccessor getAccessor(Field field) {
		String fieldName = field.getFieldName();
		switch (field.getFieldType()) {
		case BLOB:
			return new BlobAccessor(fieldName);
		case BOOLEAN:
			return new BooleanAccessor(fieldName);
		case CHAR:
			return new CharacterAccessor(fieldName);
		case DATETIME:
			return new DateTimeAccessor(fieldName);
		case DOUBLE:
			return new DoubleAccessor(fieldName);
		case FLOAT:
			return new FloatAccessor(fieldName);
		case INTEGER:
			return new IntegerAccessor(fieldName);
		case LONG:
			return new LongAccessor(fieldName);
		case SHORT:
			return new ShortAccessor(fieldName);
		default:
			return new StringAccessor(fieldName);
		}
	}

	private Collection<ITupleAccessor> getAccessors(Space space) {
		Collection<ITupleAccessor> accessors = new ArrayList<ITupleAccessor>();
		for (Field field : space.getFields()) {
			if (field == null) {
				continue;
			}
			accessors.add(getAccessor(field));
		}
		return accessors;
	}
}