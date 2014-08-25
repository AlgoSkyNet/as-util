package com.tibco.as.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tuples")
public class Tuples {

	@XmlElement(name = "tuple")
	private List<Tuple> tuples;

	public List<Tuple> getTuples() {
		if (tuples == null) {
			tuples = new ArrayList<Tuple>();
		}
		return tuples;
	}

	public void setTuples(List<Tuple> tuples) {
		this.tuples = tuples;
	}

}
