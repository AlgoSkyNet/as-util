package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class DoubleComparator extends AbstractFieldComparator<Double> {

	public DoubleComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Double value1, Double value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Double getValue(Tuple tuple, String fieldName) {
		return tuple.getDouble(fieldName);
	}

}
