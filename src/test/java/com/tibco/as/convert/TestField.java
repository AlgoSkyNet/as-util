package com.tibco.as.convert;

public class TestField extends Field {

	private Class<?> javaType;

	public TestField(TestSpace space) {
		super(space);
	}

	@Override
	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

}
