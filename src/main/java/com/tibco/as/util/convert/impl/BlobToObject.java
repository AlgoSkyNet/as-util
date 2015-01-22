package com.tibco.as.util.convert.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.util.log.LogFactory;

public class BlobToObject extends AbstractConverter<byte[], Object> {

	private Logger log = LogFactory.getLog(BlobToObject.class);

	@Override
	protected Object doConvert(byte[] source) {
		ByteArrayInputStream bais = new ByteArrayInputStream(source);
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			try {
				return ois.readObject();
			} catch (ClassNotFoundException e) {
				log.log(Level.SEVERE, "Could not deserialize object", e);
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not deserialize object", e);
		}
		return null;
	}

}
