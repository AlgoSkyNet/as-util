package com.tibco.as.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
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
import com.tibco.as.convert.converters.Base64ToBytes;
import com.tibco.as.convert.converters.BigIntegerToBytes;
import com.tibco.as.convert.converters.BooleanToBytes;
import com.tibco.as.convert.converters.BooleanToNumber;
import com.tibco.as.convert.converters.BooleanToString;
import com.tibco.as.convert.converters.BytesToBase64;
import com.tibco.as.convert.converters.BytesToBigInteger;
import com.tibco.as.convert.converters.BytesToBoolean;
import com.tibco.as.convert.converters.BytesToCharacter;
import com.tibco.as.convert.converters.BytesToDouble;
import com.tibco.as.convert.converters.BytesToFloat;
import com.tibco.as.convert.converters.BytesToHex;
import com.tibco.as.convert.converters.BytesToInteger;
import com.tibco.as.convert.converters.BytesToLong;
import com.tibco.as.convert.converters.BytesToShort;
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
import com.tibco.as.convert.converters.FloatToBytes;
import com.tibco.as.convert.converters.HexToBytes;
import com.tibco.as.convert.converters.ISO8601ToString;
import com.tibco.as.convert.converters.Idem;
import com.tibco.as.convert.converters.IntegerToBytes;
import com.tibco.as.convert.converters.LongToBigDecimal;
import com.tibco.as.convert.converters.LongToBigInteger;
import com.tibco.as.convert.converters.LongToBytes;
import com.tibco.as.convert.converters.LongToDate;
import com.tibco.as.convert.converters.NumberFormatter;
import com.tibco.as.convert.converters.NumberParser;
import com.tibco.as.convert.converters.NumberToBoolean;
import com.tibco.as.convert.converters.NumberToByte;
import com.tibco.as.convert.converters.NumberToCharacter;
import com.tibco.as.convert.converters.NumberToDouble;
import com.tibco.as.convert.converters.NumberToFloat;
import com.tibco.as.convert.converters.NumberToInteger;
import com.tibco.as.convert.converters.NumberToLong;
import com.tibco.as.convert.converters.NumberToShort;
import com.tibco.as.convert.converters.NumberToString;
import com.tibco.as.convert.converters.ShortToBytes;
import com.tibco.as.convert.converters.StringToBigDecimal;
import com.tibco.as.convert.converters.StringToBoolean;
import com.tibco.as.convert.converters.StringToByte;
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
import com.tibco.as.log.LogFactory;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef.FieldType;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConverterFactory {

	private static Logger logger = LogFactory.getLog(ConverterFactory.class);
	private Map<Class, Map<Class, Class<? extends IConverter>>> converters = new LinkedHashMap<Class, Map<Class, Class<? extends IConverter>>>();

	public ConverterFactory() {
		register(BigInteger.class, byte[].class, BigIntegerToBytes.class);
		register(Boolean.class, byte[].class, BooleanToBytes.class);
		register(Boolean.class, Number.class, BooleanToNumber.class);
		register(byte[].class, BigInteger.class, BytesToBigInteger.class);
		register(byte[].class, Boolean.class, BytesToBoolean.class);
		register(byte[].class, Character.class, BytesToCharacter.class);
		register(byte[].class, Double.class, BytesToDouble.class);
		register(byte[].class, Float.class, BytesToFloat.class);
		register(byte[].class, Integer.class, BytesToInteger.class);
		register(byte[].class, Long.class, BytesToLong.class);
		register(byte[].class, Short.class, BytesToShort.class);
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
		register(Float.class, byte[].class, FloatToBytes.class);
		register(Integer.class, byte[].class, IntegerToBytes.class);
		register(Long.class, BigInteger.class, LongToBigInteger.class);
		register(Long.class, byte[].class, LongToBytes.class);
		register(Long.class, Date.class, LongToDate.class);
		register(Long.class, BigDecimal.class, LongToBigDecimal.class);
		register(Number.class, Boolean.class, NumberToBoolean.class);
		register(Number.class, Character.class, NumberToCharacter.class);
		register(Number.class, Double.class, NumberToDouble.class);
		register(Number.class, Float.class, NumberToFloat.class);
		register(Number.class, Integer.class, NumberToInteger.class);
		register(Number.class, Long.class, NumberToLong.class);
		register(Number.class, Short.class, NumberToShort.class);
		register(Short.class, byte[].class, ShortToBytes.class);
		register(String.class, BigDecimal.class, StringToBigDecimal.class);
		register(String.class, Character.class, StringToCharacter.class);
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

	public IConverter getJavaConverter(Field field)
			throws UnsupportedConversionException {
		Class<?> from = field.getJavaType();
		Class<?> to = getJavaType(field.getFieldType());
		return getConverter(field.getConversion(), from, to);
	}

	public IConverter getFieldConverter(Field field)
			throws UnsupportedConversionException {
		Class<?> from = getJavaType(field.getFieldType());
		Class<?> to = field.getJavaType();
		return getConverter(field.getConversion(), from, to);
	}

	public IConverter getConverter(Settings conversion, Class<?> from,
			Class<?> to) throws UnsupportedConversionException {
		if (from.isAssignableFrom(to)) {
			return new Idem();
		}
		for (Class fromCandidate : converters.keySet()) {
			Map<Class, Class<? extends IConverter>> candidates = converters
					.get(fromCandidate);
			for (Class toCandidate : candidates.keySet()) {
				if (matches(from, to, fromCandidate, toCandidate)) {
					Class<?> clazz = candidates.get(toCandidate);
					try {
						return (IConverter) clazz.getConstructors()[0]
								.newInstance();
					} catch (Exception e) {
						throw new UnsupportedConversionException(from, to, e);
					}
				}
			}
		}
		if (Boolean.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				return new BooleanToString(conversion.getBooleanTruePattern(),
						conversion.getBooleanFalsePattern());
			}
		}
		if (Date.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (conversion.getDatePattern() == null) {
					return getConverter(conversion, from, Calendar.class, to);
				}
				return new DateToString(getDateFormat(conversion));
			}
		}
		if (Calendar.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (conversion.getDatePattern() == null) {
					return new ISO8601ToString();
				}
				return getConverter(conversion, from, Date.class, to);
			}
		}
		if (String.class.isAssignableFrom(from)) {
			if (Boolean.class.isAssignableFrom(to)) {
				return new StringToBoolean(conversion.getBooleanTruePattern());
			}
			if (Byte.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new StringToByte();
				}
				return getNumberConverter(pattern, new NumberToByte());
			}
			if (Double.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new StringToDouble();
				}
				return getNumberConverter(pattern, new NumberToDouble());
			}
			if (Float.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new StringToFloat();
				}
				return getNumberConverter(pattern, new NumberToFloat());
			}
			if (Integer.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new StringToInteger();
				}
				return getNumberConverter(pattern, new NumberToInteger());
			}
			if (Long.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new StringToLong();
				}
				return getNumberConverter(pattern, new NumberToLong());
			}
			if (Short.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new StringToShort();
				}
				return getNumberConverter(pattern, new NumberToShort());
			}
			if (Date.class.isAssignableFrom(to)) {
				if (conversion.getDatePattern() == null) {
					return getConverter(conversion, from, Calendar.class, to);
				}
				return new StringToDate(getDateFormat(conversion));
			}
			if (Calendar.class.isAssignableFrom(to)) {
				if (conversion.getDatePattern() == null) {
					return new StringToISO8601();
				}
				return getConverter(conversion, from, Date.class, to);
			}
			if (DateTime.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Calendar.class, to);
			}
			if (byte[].class.isAssignableFrom(to)) {
				switch (conversion.getBlob()) {
				case BASE64:
					return new Base64ToBytes();
				default:
					return new HexToBytes();
				}
			}
		}
		if (DateTime.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Calendar.class, to);
			}
			if (Long.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Date.class, to);
			}
			if (Number.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Long.class, to);
			}
		}
		if (Long.class.isAssignableFrom(from)) {
			if (DateTime.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Date.class, to);
			}
		}
		if (Number.class.isAssignableFrom(from)) {
			if (DateTime.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Long.class, to);
			}
			if (BigDecimal.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Long.class, to);
			}
			if (BigInteger.class.isAssignableFrom(to)) {
				return getConverter(conversion, from, Long.class, to);
			}
			if (String.class.isAssignableFrom(to)) {
				String pattern = conversion.getNumberPattern();
				if (pattern == null) {
					return new NumberToString();
				}
				return new NumberFormatter(pattern);
			}
		}
		if (byte[].class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				switch (conversion.getBlob()) {
				case BASE64:
					return new BytesToBase64();
				default:
					return new BytesToHex();
				}
			}
		}
		throw new UnsupportedConversionException(from, to);
	}

	private DateFormat getDateFormat(Settings conversion) {
		String pattern = conversion.getDatePattern();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(conversion.getTimeZone());
		return format;
	}

	private IConverter getNumberConverter(String pattern, IConverter converter2) {
		NumberParser converter1 = new NumberParser(pattern);
		return new ChainedConverter(converter1, converter2);
	}

	private IConverter getConverter(Settings conversion, Class<?> from,
			Class<?> pivot, Class<?> to) throws UnsupportedConversionException {
		IConverter converter1 = getConverter(conversion, from, pivot);
		IConverter converter2 = getConverter(conversion, pivot, to);
		return new ChainedConverter(converter1, converter2);
	}

	private Class<?> getJavaType(FieldType fieldType) {
		if (fieldType == null) {
			return String.class;
		}
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

	private boolean matches(Class from, Class to, Class candidateFrom,
			Class candidateTo) {
		return candidateFrom.isAssignableFrom(from)
				&& candidateTo.isAssignableFrom(to);
	}

	public IConverter getJavaConverter(Collection<Field> fields)
			throws UnsupportedConversionException {
		Collection<ITupleAccessor> accessors = getAccessors(fields);
		Collection<IConverter> converters = getJavaConverters(fields);
		return new ArrayConverter(accessors, converters);
	}

	private Collection<IConverter> getJavaConverters(Collection<Field> fields)
			throws UnsupportedConversionException {
		Collection<IConverter> converters = new ArrayList<IConverter>();
		for (Field field : fields) {
			converters.add(getJavaConverter(field));
		}
		return converters;
	}

	public IConverter getTupleConverter(Collection<Field> fields,
			Class<?> componentType) throws UnsupportedConversionException {
		Collection<ITupleAccessor> accessors = getAccessors(fields);
		Collection<IConverter> converters = getFieldConverters(fields);
		return new TupleConverter(accessors, converters, componentType);
	}

	private Collection<IConverter> getFieldConverters(Collection<Field> fields)
			throws UnsupportedConversionException {
		Collection<IConverter> converters = new ArrayList<IConverter>();
		for (Field field : fields) {
			converters.add(getFieldConverter(field));
		}
		return converters;
	}

	private ITupleAccessor getAccessor(Field field) {
		if (field.getFieldType() == null) {
			return null;
		}
		switch (field.getFieldType()) {
		case BLOB:
			return new BlobAccessor(field.getFieldName());
		case BOOLEAN:
			return new BooleanAccessor(field.getFieldName());
		case CHAR:
			return new CharacterAccessor(field.getFieldName());
		case DATETIME:
			return new DateTimeAccessor(field.getFieldName());
		case DOUBLE:
			return new DoubleAccessor(field.getFieldName());
		case FLOAT:
			return new FloatAccessor(field.getFieldName());
		case INTEGER:
			return new IntegerAccessor(field.getFieldName());
		case LONG:
			return new LongAccessor(field.getFieldName());
		case SHORT:
			return new ShortAccessor(field.getFieldName());
		default:
			return new StringAccessor(field.getFieldName());
		}
	}

	private Collection<ITupleAccessor> getAccessors(Collection<Field> fields) {
		Collection<ITupleAccessor> accessors = new ArrayList<ITupleAccessor>();
		for (Field field : fields) {
			accessors.add(getAccessor(field));
		}
		return accessors;
	}
}