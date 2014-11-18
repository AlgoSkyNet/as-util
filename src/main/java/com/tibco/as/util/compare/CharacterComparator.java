package com.tibco.as.util.compare;

import com.tibco.as.space.Tuple;

public class CharacterComparator extends AbstractFieldComparator<Character> {

	public CharacterComparator(String fieldName) {
		super(fieldName);
	}

	@Override
	protected int compareFields(Character value1, Character value2) {
		return value1.compareTo(value2);
	}

	@Override
	protected Character getValue(Tuple tuple, String fieldName) {
		return tuple.getChar(fieldName);
	}

}
