package com.tibco.as.util.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;

public class TupleComparator implements Comparator<Tuple> {

	private ITupleComparator[] comparators;

	public TupleComparator(ITupleComparator... comparators) {
		this.comparators = comparators;
	}

	public static TupleComparator create(SpaceDef spaceDef,
			String... fieldNames) {
		return new TupleComparator(getComparators(spaceDef, fieldNames));
	}

	private static ITupleComparator[] getComparators(SpaceDef spaceDef,
			String[] fieldNames) {
		Collection<ITupleComparator> comparators = new ArrayList<ITupleComparator>();
		for (FieldDef fieldDef : getFieldDefs(spaceDef, fieldNames)) {
			comparators.add(getComparator(fieldDef));
		}
		return comparators.toArray(new ITupleComparator[comparators.size()]);
	}

	private static ITupleComparator getComparator(FieldDef fieldDef) {
		String fieldName = fieldDef.getName();
		switch (fieldDef.getType()) {
		case BLOB:
			return new BlobComparator(fieldName);
		case BOOLEAN:
			return new BooleanComparator(fieldName);
		case CHAR:
			return new CharacterComparator(fieldName);
		case DATETIME:
			return new DateTimeComparator(fieldName);
		case DOUBLE:
			return new DoubleComparator(fieldName);
		case FLOAT:
			return new FloatComparator(fieldName);
		case INTEGER:
			return new IntegerComparator(fieldName);
		case LONG:
			return new LongComparator(fieldName);
		case SHORT:
			return new ShortComparator(fieldName);
		case STRING:
			return new StringComparator(fieldName);
		}
		return null;
	}

	private static Collection<FieldDef> getFieldDefs(SpaceDef spaceDef,
			String[] fieldNames) {
		if (fieldNames.length == 0) {
			return Collections.unmodifiableCollection(spaceDef.getFieldDefs());
		}
		Collection<FieldDef> fieldDefs = new ArrayList<FieldDef>();
		for (String fieldName : fieldNames) {
			fieldDefs.add(spaceDef.getFieldDef(fieldName));
		}
		return fieldDefs;
	}

	@Override
	public int compare(Tuple tuple1, Tuple tuple2) {
		if (tuple1 == null) {
			if (tuple2 == null) {
				return 0;
			}
			return -1;
		}
		if (tuple2 == null) {
			return 1;
		}
		for (ITupleComparator comparator : comparators) {
			int comparison = comparator.compare(tuple1, tuple2);
			if (comparison != 0) {
				return comparison;
			}
		}
		return 0;
	}

}
