package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
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

	public static ITupleAccessor create(FieldDef fieldDef) {
		switch (fieldDef.getType()) {
		case BLOB:
			return new BlobAccessor(fieldDef);
		case BOOLEAN:
			return new BooleanAccessor(fieldDef);
		case CHAR:
			return new CharacterAccessor(fieldDef);
		case DATETIME:
			return new DateTimeAccessor(fieldDef);
		case DOUBLE:
			return new DoubleAccessor(fieldDef);
		case FLOAT:
			return new FloatAccessor(fieldDef);
		case INTEGER:
			return new IntegerAccessor(fieldDef);
		case LONG:
			return new LongAccessor(fieldDef);
		case SHORT:
			return new ShortAccessor(fieldDef);
		default:
			return new StringAccessor(fieldDef);
		}
	}

	public static ITupleAccessor[] create(FieldDef[] fieldDefs) {
		ITupleAccessor[] accessors = new ITupleAccessor[fieldDefs.length];
		for (int index = 0; index < fieldDefs.length; index++) {
			accessors[index] = create(fieldDefs[index]);
		}
		return accessors;
	}

	public static ITupleAccessor[] create(SpaceDef spaceDef) {
		return create(spaceDef.getFieldDefs().toArray(
				new FieldDef[spaceDef.getFieldDefs().size()]));
	}

}
