package com.tibco.as.util.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.util.convert.IAccessor;

public class TupleAccessorFactory {

	public static IAccessor create(FieldDef fieldDef) {
		FieldType type = fieldDef.getType();
		if (type == null) {
			return null;
		}
		String fieldName = fieldDef.getName();
		switch (type) {
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
}
