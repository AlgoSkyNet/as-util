package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class ShortComparator extends AbstractFieldComparator<Short> {

	public ShortComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Short value1, Short value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Short getValue(Tuple tuple, String fieldName) {
		return tuple.getShort(fieldName);
	}

}
