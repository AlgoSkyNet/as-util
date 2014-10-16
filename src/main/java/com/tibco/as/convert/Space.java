package com.tibco.as.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.log.LogFactory;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.SpaceDef;

public class Space {

	private final static String DEFAULT_BOOLEAN_FORMAT = "true|false";
	private final static TimeZone DEFAULT_TIMEZONE = TimeZone
			.getTimeZone("GMT");

	private Logger log = LogFactory.getLog(Space.class);
	private String space;
	private Collection<Field> fields = new ArrayList<Field>();
	private Collection<String> keys = new ArrayList<String>();
	private Blob blobFormat;
	private String booleanFormat;
	private String dateFormat;
	private TimeZone timeZone;
	private String decimalFormat;
	private String integerFormat;

	public void setFields(Collection<Field> fields) {
		this.fields = fields;
	}

	public Blob getBlobFormat() {
		return blobFormat;
	}

	public void setBlobFormat(Blob blobFormat) {
		this.blobFormat = blobFormat;
	}

	public String getBooleanFormat() {
		if (booleanFormat == null) {
			return DEFAULT_BOOLEAN_FORMAT;
		}
		return booleanFormat;
	}

	public void setBooleanFormat(String booleanFormat) {
		this.booleanFormat = booleanFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public TimeZone getTimeZone() {
		if (timeZone == null) {
			return DEFAULT_TIMEZONE;
		}
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(String decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public String getIntegerFormat() {
		return integerFormat;
	}

	public void setIntegerFormat(String integerFormat) {
		this.integerFormat = integerFormat;
	}

	public void copyTo(Space target) {
		target.blobFormat = blobFormat;
		target.booleanFormat = booleanFormat;
		target.dateFormat = dateFormat;
		target.decimalFormat = decimalFormat;
		for (Field field : fields) {
			target.fields.add(field.clone());
		}
		target.integerFormat = integerFormat;
		target.keys = new ArrayList<String>(keys);
		target.space = space;
		target.timeZone = timeZone;
	}

	public Collection<Field> getFields() {
		return Collections.unmodifiableCollection(fields);
	}

	protected Field getField(String name) {
		for (Field field : fields) {
			if (field.getFieldName().equals(name)) {
				return field;
			}
		}
		return null;
	}

	public Collection<String> getKeys() {
		return keys;
	}

	public void setKeys(Collection<String> keys) {
		this.keys = keys;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public void setSpaceDef(SpaceDef spaceDef) {
		setSpace(spaceDef.getName());
		setKeys(spaceDef.getKeyDef().getFieldNames());
		if (fields.isEmpty()) {
			for (FieldDef fieldDef : spaceDef.getFieldDefs()) {
				addField().setFieldName(fieldDef.getName());
			}
		}
		for (Field field : fields) {
			FieldDef fieldDef = spaceDef.getFieldDef(field.getFieldName());
			if (fieldDef == null) {
				log.log(Level.WARNING,
						"No field named ''{0}'' in space ''{1}''",
						new Object[] { field.getFieldName(), spaceDef.getName() });
			} else {
				field.setFieldType(fieldDef.getType());
				field.setFieldNullable(fieldDef.isNullable());
				field.setFieldEncrypted(fieldDef.isEncrypted());
			}
		}
		setKeys(spaceDef.getKeyDef().getFieldNames());
	}

	public Field addField() {
		Field field = newField();
		field.setSpace(this);
		fields.add(field);
		return field;
	}

	protected Field newField() {
		return new Field();
	}

	public SpaceDef getSpaceDef() {
		SpaceDef spaceDef = SpaceDef.create(getSpace());
		for (Field field : fields) {
			spaceDef.getFieldDefs().add(field.getFieldDef());
		}
		Collection<String> keys = getKeys();
		spaceDef.setKey(keys.toArray(new String[keys.size()]));
		return spaceDef;
	}
}
