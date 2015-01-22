package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class StringComparator extends AbstractFieldComparator<String> {

	public StringComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(String value1, String value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected String getValue(Tuple tuple, String fieldName) {
		return tuple.getString(fieldName);
	}

}
