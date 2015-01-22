package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class LongComparator extends AbstractFieldComparator<Long> {

	public LongComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Long value1, Long value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Long getValue(Tuple tuple, String fieldName) {
		return tuple.getLong(fieldName);
	}

}
