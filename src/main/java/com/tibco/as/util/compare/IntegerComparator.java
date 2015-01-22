package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class IntegerComparator extends AbstractFieldComparator<Integer> {

	public IntegerComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Integer value1, Integer value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Integer getValue(Tuple tuple, String fieldName) {
		return tuple.getInt(fieldName);
	}

}
