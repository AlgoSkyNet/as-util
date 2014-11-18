package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class BooleanComparator extends AbstractFieldComparator<Boolean> {

	public BooleanComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Boolean value1, Boolean value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Boolean getValue(Tuple tuple, String fieldName) {
		return tuple.getBoolean(fieldName);
	}

}
