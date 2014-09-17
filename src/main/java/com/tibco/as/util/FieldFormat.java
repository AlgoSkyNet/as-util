package com.tibco.as.util;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.StringTokenizer;

import com.tibco.as.space.FieldDef.FieldType;

public class FieldFormat extends Format {

	private final static String NAME = "name";
	private final static String TYPE = "type";
	private final static String NULLABLE = "nullable";
	private final static String ENCRYPTED = "encrypted";
	private final static String KEY = "key";

	private static final long serialVersionUID = -2534441437805237858L;

	private static final char QUOTE = '\'';
	private static final char SEPARATOR = ' ';

	public String format(com.tibco.as.util.Field field) {
		return super.format(field);
	}

	@Override
	public com.tibco.as.util.Field parseObject(String source)
			throws ParseException {
		return (com.tibco.as.util.Field) super.parseObject(source);
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer buffer,
			FieldPosition pos) {
		com.tibco.as.util.Field field = (com.tibco.as.util.Field) obj;
		if (field.getName() == null) {
			return buffer;
		}
		buffer.append(NAME);
		addSeparator(buffer);
		String string = String.valueOf(field.getName());
		quote(buffer, string);
		if (field.getType() != null) {
			add(buffer, TYPE, field.getType().name().toLowerCase(), true);
		}
		if (field.isNullable()) {
			add(buffer, NULLABLE, field.isNullable(), false);
		}
		if (field.isEncrypted()) {
			add(buffer, ENCRYPTED, field.isEncrypted(), false);
		}
		if (field.isKey()) {
			add(buffer, KEY, field.isKey(), false);
		}
		return buffer;

	}

	private void add(StringBuffer buffer, String property, Object value,
			boolean quote) {
		addSeparator(buffer);
		buffer.append(property);
		addSeparator(buffer);
		String string = String.valueOf(value);
		if (quote) {
			quote(buffer, string);
		} else {
			buffer.append(string);
		}
	}

	private void addSeparator(StringBuffer buffer) {
		buffer.append(SEPARATOR);
	}

	private void quote(StringBuffer buffer, String string) {
		buffer.append(QUOTE);
		buffer.append(string);
		buffer.append(QUOTE);
	}

	@Override
	public com.tibco.as.util.Field parseObject(String source, ParsePosition pos) {
		com.tibco.as.util.Field field = new com.tibco.as.util.Field();
		if (source == null) {
			return field;
		}
		StringTokenizer tokenizer = new StringTokenizer(source);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (tokenizer.hasMoreTokens()) {
				if (NAME.equals(token)) {
					field.setName(getString(tokenizer));
				} else if (TYPE.equals(token)) {
					String typeName = getString(tokenizer);
					if (typeName != null) {
						field.setType(FieldType.valueOf(typeName.toUpperCase()));
					}
				} else if (NULLABLE.equals(token)) {
					field.setNullable(getBoolean(tokenizer));
				} else {
					if (ENCRYPTED.equals(token)) {
						field.setEncrypted(getBoolean(tokenizer));
					} else if (KEY.equals(token)) {
						field.setKey(getBoolean(tokenizer));
					} else {
						field.setName(token);
					}
				}
			} else {
				field.setName(token);
			}
		}
		pos.setIndex(source.length());
		return field;
	}

	private boolean getBoolean(StringTokenizer tokenizer) {
		if (tokenizer.hasMoreTokens()) {
			return Boolean.valueOf(tokenizer.nextToken().toLowerCase());
		}
		return false;
	}

	private String getString(StringTokenizer tokenizer) {
		if (tokenizer.hasMoreTokens()) {
			return unquote(tokenizer.nextToken());
		}
		return null;
	}

	private String unquote(String token) {
		int length = token.length();
		if (length > 0) {
			if (token.charAt(0) == QUOTE) {
				int lastQuoteIndex = token.length() - 1;
				if (token.charAt(lastQuoteIndex) == QUOTE) {
					return token.substring(1, lastQuoteIndex);
				}
			}
		}
		return token;
	}
}
