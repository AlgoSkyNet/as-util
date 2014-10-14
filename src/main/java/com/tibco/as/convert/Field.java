package com.tibco.as.convert;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;

public class Field implements Cloneable {

	private Space space;
	private String fieldName;
	private Boolean fieldNullable;
	private Boolean fieldEncrypted;
	private FieldType fieldType;
	private Blob blobFormat;
	private String booleanFormat;
	private String dateFormat;
	private TimeZone timeZone;
	private String decimalFormat;
	private String integerFormat;

	public Field(Space space) {
		this.space = space;
	}

	public Blob getBlobFormat() {
		if (blobFormat == null) {
			return space.getBlobFormat();
		}
		return blobFormat;
	}

	public void setBlobFormat(Blob blobFormat) {
		this.blobFormat = blobFormat;
	}

	public String getBooleanFormat() {
		if (booleanFormat == null) {
			return space.getBooleanFormat();
		}
		return booleanFormat;
	}

	public void setBooleanFormat(String booleanFormat) {
		this.booleanFormat = booleanFormat;
	}

	public String getDateFormat() {
		if (dateFormat == null) {
			return space.getDateFormat();
		}
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public TimeZone getTimeZone() {
		if (timeZone == null) {
			return space.getTimeZone();
		}
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getDecimalFormat() {
		if (decimalFormat == null) {
			return space.getDecimalFormat();
		}
		return decimalFormat;
	}

	public void setDecimalFormat(String decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public String getIntegerFormat() {
		if (integerFormat == null) {
			return space.getIntegerFormat();
		}
		return integerFormat;
	}

	public void setIntegerFormat(String integerFormat) {
		this.integerFormat = integerFormat;
	}

	public Class<?> getJavaType() {
		return getJavaType(getFieldType());
	}

	public FieldType getFieldType() {
		if (fieldType == null) {
			return getFieldType(getJavaType());
		}
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public static FieldType getFieldType(Class<?> javaType) {
		if (javaType == null) {
			return null;
		}
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

	public void setSpace(Space space) {
		this.space = space;
	}

	public Space getSpace() {
		return space;
	}

	public Boolean getFieldNullable() {
		return fieldNullable;
	}

	public void setFieldNullable(Boolean nullable) {
		this.fieldNullable = nullable;
	}

	public Boolean getFieldEncrypted() {
		return fieldEncrypted;
	}

	public void setFieldEncrypted(Boolean encrypted) {
		this.fieldEncrypted = encrypted;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public FieldDef getFieldDef() {
		FieldDef fieldDef = FieldDef.create(getFieldName(), getFieldType());
		Boolean encrypted = getFieldEncrypted();
		if (encrypted != null) {
			fieldDef.setEncrypted(encrypted);
		}
		Boolean nullable = getFieldNullable();
		if (nullable != null) {
			fieldDef.setNullable(nullable);
		}
		return fieldDef;
	}

	public void setFieldDef(FieldDef fieldDef) {
		setFieldName(fieldDef.getName());
		setFieldType(fieldDef.getType());
		setFieldNullable(fieldDef.isNullable());
		setFieldEncrypted(fieldDef.isEncrypted());
	}

	public Field clone() {
		Field clone = new Field(space);
		copyTo(clone);
		return clone;
	}

	public void copyTo(Field target) {
		target.blobFormat = blobFormat;
		target.booleanFormat = booleanFormat;
		target.dateFormat = dateFormat;
		target.decimalFormat = decimalFormat;
		target.fieldEncrypted = fieldEncrypted;
		target.fieldName = fieldName;
		target.fieldNullable = fieldNullable;
		target.fieldType = fieldType;
		target.integerFormat = integerFormat;
		target.timeZone = timeZone;
	}

	public static Class<?> getJavaType(FieldType fieldType) {
		if (fieldType == null) {
			return null;
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

	public boolean isImport() {
		return space.isImport();
	}

}
