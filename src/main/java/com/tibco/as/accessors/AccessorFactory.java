package com.tibco.as.accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.SpaceDef;

/**
 * A factory for building/retrieving FieldAccessor instances.
 * 
 * @author Julien Ruaux
 */
public class AccessorFactory {

	public final static int DOUBLE_SIZE = Double.toString(Math.PI).length();
	public final static int FLOAT_SIZE = Float.toString((float) Math.PI)
			.length();
	public final static int LONG_SIZE = Long.toString(Long.MAX_VALUE).length();
	public final static int INTEGER_SIZE = Integer.toString(Integer.MAX_VALUE)
			.length();
	public final static int SHORT_SIZE = Short.toString(Short.MAX_VALUE)
			.length();

	public static ITupleAccessor create(String fieldName, FieldType fieldType) {
		switch (fieldType) {
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

	public static ITupleAccessor[] create(FieldDef... fieldDefs) {
		Collection<ITupleAccessor> accessors = create(Arrays.asList(fieldDefs));
		return accessors.toArray(new ITupleAccessor[accessors.size()]);
	}

	private static Collection<ITupleAccessor> create(
			Collection<FieldDef> fieldDefs) {
		Collection<ITupleAccessor> accessors = new ArrayList<ITupleAccessor>();
		for (FieldDef fieldDef : fieldDefs) {
			if (fieldDef == null) {
				continue;
			}
			accessors.add(create(fieldDef.getName(), fieldDef.getType()));
		}
		return accessors;
	}

	public static Collection<ITupleAccessor> create(SpaceDef spaceDef) {
		return create(spaceDef.getFieldDefs());
	}

}
