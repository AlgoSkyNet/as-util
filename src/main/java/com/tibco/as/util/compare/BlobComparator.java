package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class BlobComparator extends AbstractFieldComparator<byte[]> {

	public BlobComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(byte[] value1, byte[] value2) {
		for (int i = 0, j = 0; i < value1.length && j < value2.length; i++, j++) {
			if (value1[i] != value2[j]) {
				return value1[i] - value2[j];
			}
		}
		return value1.length - value2.length;
	}

	@Override
	protected byte[] getValue(Tuple tuple, String fieldName) {
		return tuple.getBlob(fieldName);
	}

}
