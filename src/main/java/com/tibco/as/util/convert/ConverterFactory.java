package com.tibco.as.util.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.util.convert.impl.Base64ToBytes;
import com.tibco.as.util.convert.impl.BigIntegerToBytes;
import com.tibco.as.util.convert.impl.BooleanToBytes;
import com.tibco.as.util.convert.impl.BooleanToNumber;
import com.tibco.as.util.convert.impl.BooleanToString;
import com.tibco.as.util.convert.impl.BytesToBase64;
import com.tibco.as.util.convert.impl.BytesToBigInteger;
import com.tibco.as.util.convert.impl.BytesToBoolean;
import com.tibco.as.util.convert.impl.BytesToCharacter;
import com.tibco.as.util.convert.impl.BytesToDouble;
import com.tibco.as.util.convert.impl.BytesToFloat;
import com.tibco.as.util.convert.impl.BytesToHex;
import com.tibco.as.util.convert.impl.BytesToInteger;
import com.tibco.as.util.convert.impl.BytesToLong;
import com.tibco.as.util.convert.impl.BytesToShort;
import com.tibco.as.util.convert.impl.CalendarToDate;
import com.tibco.as.util.convert.impl.CalendarToDateTime;
import com.tibco.as.util.convert.impl.CharacterToBytes;
import com.tibco.as.util.convert.impl.CharacterToNumber;
import com.tibco.as.util.convert.impl.CharacterToString;
import com.tibco.as.util.convert.impl.DateTimeToCalendar;
import com.tibco.as.util.convert.impl.DateTimeToDate;
import com.tibco.as.util.convert.impl.DateTimeToSQLDate;
import com.tibco.as.util.convert.impl.DateTimeToSQLTime;
import com.tibco.as.util.convert.impl.DateTimeToSQLTimestamp;
import com.tibco.as.util.convert.impl.DateToCalendar;
import com.tibco.as.util.convert.impl.DateToDateTime;
import com.tibco.as.util.convert.impl.DateToLong;
import com.tibco.as.util.convert.impl.DateToString;
import com.tibco.as.util.convert.impl.DoubleToBigDecimal;
import com.tibco.as.util.convert.impl.DoubleToBytes;
import com.tibco.as.util.convert.impl.DynamicConverter;
import com.tibco.as.util.convert.impl.FloatToBytes;
import com.tibco.as.util.convert.impl.HexToBytes;
import com.tibco.as.util.convert.impl.ISO8601ToString;
import com.tibco.as.util.convert.impl.Idem;
import com.tibco.as.util.convert.impl.IntegerToBytes;
import com.tibco.as.util.convert.impl.LongToBigDecimal;
import com.tibco.as.util.convert.impl.LongToBigInteger;
import com.tibco.as.util.convert.impl.LongToBytes;
import com.tibco.as.util.convert.impl.LongToDate;
import com.tibco.as.util.convert.impl.NumberFormatter;
import com.tibco.as.util.convert.impl.NumberParser;
import com.tibco.as.util.convert.impl.NumberToBoolean;
import com.tibco.as.util.convert.impl.NumberToByte;
import com.tibco.as.util.convert.impl.NumberToCharacter;
import com.tibco.as.util.convert.impl.NumberToDouble;
import com.tibco.as.util.convert.impl.NumberToFloat;
import com.tibco.as.util.convert.impl.NumberToInteger;
import com.tibco.as.util.convert.impl.NumberToLong;
import com.tibco.as.util.convert.impl.NumberToShort;
import com.tibco.as.util.convert.impl.NumberToString;
import com.tibco.as.util.convert.impl.ShortToBytes;
import com.tibco.as.util.convert.impl.StringToBigDecimal;
import com.tibco.as.util.convert.impl.StringToBoolean;
import com.tibco.as.util.convert.impl.StringToByte;
import com.tibco.as.util.convert.impl.StringToCharacter;
import com.tibco.as.util.convert.impl.StringToDate;
import com.tibco.as.util.convert.impl.StringToDouble;
import com.tibco.as.util.convert.impl.StringToFloat;
import com.tibco.as.util.convert.impl.StringToISO8601;
import com.tibco.as.util.convert.impl.StringToInteger;
import com.tibco.as.util.convert.impl.StringToLong;
import com.tibco.as.util.convert.impl.StringToShort;
import com.tibco.as.util.convert.impl.StringToURL;
import com.tibco.as.util.convert.impl.URLToString;
import com.tibco.as.util.log.LogFactory;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConverterFactory {

	private Logger log = LogFactory.getLog(ConverterFactory.class);
	private Map<Class, Map<Class, Class<? extends IConverter>>> converters = new LinkedHashMap<Class, Map<Class, Class<? extends IConverter>>>();
	private Settings settings = new Settings();

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

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	private void register(Class from, Class to,
			Class<? extends IConverter> converter) {
		if (!converters.containsKey(from)) {
			converters.put(from,
					new LinkedHashMap<Class, Class<? extends IConverter>>());
		}
		Map<Class, Class<? extends IConverter>> map = converters.get(from);
		if (map.containsKey(to)) {
			log.warning(MessageFormat.format("Duplicate converters: {0} - {1}",
					map.get(to).getName(), converter.getName()));
		}
		map.put(to, converter);

	}

	public IConverter getConverter(Class<?> from, Class<?> to) {
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
						String msg = MessageFormat.format(
								"Could not instantiate {0}", clazz);
						log.log(Level.SEVERE, msg, e);
					}
				}
			}
		}
		if (Boolean.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				return new BooleanToString(settings.getBooleanTruePattern(),
						settings.getBooleanFalsePattern());
			}
			if (Date.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
		}
		if (Date.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (settings.getDatePattern() == null) {
					return getConverter(from, Calendar.class, to);
				}
				return new DateToString(getDateFormat());
			}
			if (byte[].class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
			if (Boolean.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
			if (Character.class.isAssignableFrom(to)) {
				return getConverter(from, String.class, to);
			}
		}
		if (Calendar.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				if (settings.getDatePattern() == null) {
					return new ISO8601ToString();
				}
				return getConverter(from, Date.class, to);
			}
		}
		if (String.class.isAssignableFrom(from)) {
			if (Boolean.class.isAssignableFrom(to)) {
				return new StringToBoolean(settings.getBooleanTruePattern());
			}
			if (Byte.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new StringToByte();
				}
				return getNumberConverter(pattern, new NumberToByte());
			}
			if (Double.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new StringToDouble();
				}
				return getNumberConverter(pattern, new NumberToDouble());
			}
			if (Float.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new StringToFloat();
				}
				return getNumberConverter(pattern, new NumberToFloat());
			}
			if (Integer.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new StringToInteger();
				}
				return getNumberConverter(pattern, new NumberToInteger());
			}
			if (Long.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new StringToLong();
				}
				return getNumberConverter(pattern, new NumberToLong());
			}
			if (Short.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new StringToShort();
				}
				return getNumberConverter(pattern, new NumberToShort());
			}
			if (Date.class.isAssignableFrom(to)) {
				if (settings.getDatePattern() == null) {
					return getConverter(from, Calendar.class, to);
				}
				return new StringToDate(getDateFormat());
			}
			if (Calendar.class.isAssignableFrom(to)) {
				if (settings.getDatePattern() == null) {
					return new StringToISO8601();
				}
				return getConverter(from, Date.class, to);
			}
			if (DateTime.class.isAssignableFrom(to)) {
				return getConverter(from, Calendar.class, to);
			}
			if (byte[].class.isAssignableFrom(to)) {
				switch (settings.getBlob()) {
				case BASE64:
					return new Base64ToBytes();
				default:
					return new HexToBytes();
				}
			}
		}
		if (DateTime.class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				return getConverter(from, Calendar.class, to);
			}
			if (Long.class.isAssignableFrom(to)) {
				return getConverter(from, Date.class, to);
			}
			if (Number.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
		}
		if (Long.class.isAssignableFrom(from)) {
			if (DateTime.class.isAssignableFrom(to)) {
				return getConverter(from, Date.class, to);
			}
		}
		if (Number.class.isAssignableFrom(from)) {
			if (DateTime.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
			if (BigDecimal.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
			if (BigInteger.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
			if (String.class.isAssignableFrom(to)) {
				String pattern = settings.getNumberPattern();
				if (pattern == null) {
					return new NumberToString();
				}
				return new NumberFormatter(new DecimalFormat(pattern));
			}
		}
		if (byte[].class.isAssignableFrom(from)) {
			if (String.class.isAssignableFrom(to)) {
				switch (settings.getBlob()) {
				case BASE64:
					return new BytesToBase64();
				default:
					return new BytesToHex();
				}
			}
			if (Date.class.isAssignableFrom(to)) {
				return getConverter(from, Long.class, to);
			}
		}
		if (Character.class.isAssignableFrom(from)) {
			if (Date.class.isAssignableFrom(to)) {
				return getConverter(from, String.class, to);
			}
		}
		if (Object.class.equals(from)) {
			return new DynamicConverter(this, to);
		}
		if (Object.class.equals(to)) {
			return new Idem();
		}
		if (from.isAssignableFrom(to)) {
			return new Idem();
		}
		return null;
	}

	private DateFormat getDateFormat() {
		String pattern = settings.getDatePattern();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(settings.getTimeZone());
		return format;
	}

	private IConverter getNumberConverter(String pattern, IConverter converter2) {
		NumberParser converter1 = new NumberParser(new DecimalFormat(pattern));
		return new ChainedConverter(converter1, converter2);
	}

	private IConverter getConverter(Class<?> from, Class<?> pivot, Class<?> to) {
		IConverter converter1 = getConverter(from, pivot);
		IConverter converter2 = getConverter(pivot, to);
		return new ChainedConverter(converter1, converter2);
	}

	public static Class<?> getJavaType(FieldType fieldType) {
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

	public static FieldType getFieldType(Class<?> javaType) {
		if (byte[].class.isAssignableFrom(javaType)) {
			return FieldType.BLOB;
		}
		if (Boolean.class.isAssignableFrom(javaType)) {
			return FieldType.BOOLEAN;
		}
		if (Character.class.isAssignableFrom(javaType)) {
			return FieldType.CHAR;
		}
		if (DateTime.class.isAssignableFrom(javaType)) {
			return FieldType.DATETIME;
		}
		if (Calendar.class.isAssignableFrom(javaType)) {
			return FieldType.DATETIME;
		}
		if (Date.class.isAssignableFrom(javaType)) {
			return FieldType.DATETIME;
		}
		if (Double.class.isAssignableFrom(javaType)) {
			return FieldType.DOUBLE;
		}
		if (Float.class.isAssignableFrom(javaType)) {
			return FieldType.FLOAT;
		}
		if (Integer.class.isAssignableFrom(javaType)) {
			return FieldType.INTEGER;
		}
		if (Long.class.isAssignableFrom(javaType)) {
			return FieldType.LONG;
		}
		if (Short.class.isAssignableFrom(javaType)) {
			return FieldType.SHORT;
		}
		return FieldType.STRING;
	}
}