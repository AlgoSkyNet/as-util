package com.tibco.as.convert;

public class ChainedConversion {

	private Conversion conversion1;

	private Conversion conversion2;

	public ChainedConversion(Conversion conversion1, Conversion conversion2) {
		this.conversion1 = conversion1;
		this.conversion2 = conversion2;
	}

	public Conversion getConversion1() {
		return conversion1;
	}

	public void setConversion1(Conversion conversion1) {
		this.conversion1 = conversion1;
	}

	public Conversion getConversion2() {
		return conversion2;
	}

	public void setConversion2(Conversion conversion2) {
		this.conversion2 = conversion2;
	}
}
