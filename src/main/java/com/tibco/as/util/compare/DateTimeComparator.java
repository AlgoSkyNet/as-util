package com.tibco.as.util.compare;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.Tuple;

public class DateTimeComparator extends AbstractFieldComparator<DateTime> {

	public DateTimeComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(DateTime value1, DateTime value2) {
		return value1.getTime().compareTo(value2.getTime());
	}

	@Override
	protected DateTime getValue(Tuple tuple, String fieldName) {
		return tuple.getDateTime(fieldName);
	}

}
