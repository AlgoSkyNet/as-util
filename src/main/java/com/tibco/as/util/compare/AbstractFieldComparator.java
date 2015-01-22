package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public abstract class AbstractFieldComparator<T> implements ITupleComparator {

	private String fieldName;

	public AbstractFieldComparator(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public int compare(Tuple tuple1, Tuple tuple2) {
		T value1 = getValue(tuple1, fieldName);
		T value2 = getValue(tuple2, fieldName);
		if (value1 == null) {
			if (value2 == null) {
				return 0;
			}
			return -1;
		}
		if (value2 == null) {
			return 1;
		}
		return compareFields(value1, value2);
	}

	protected abstract int compareFields(T value1, T value2);

	protected abstract T getValue(Tuple tuple, String fieldName);

}
