package com.tibco.as.convert;

public class TestSpace extends Space {

	@Override
	public Field createField() {
		return new TestField(this);
	}
}
