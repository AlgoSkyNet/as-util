package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class FloatComparator extends AbstractFieldComparator<Float> {

	public FloatComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Float value1, Float value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Float getValue(Tuple tuple, String fieldName) {
		return tuple.getFloat(fieldName);
	}

}
