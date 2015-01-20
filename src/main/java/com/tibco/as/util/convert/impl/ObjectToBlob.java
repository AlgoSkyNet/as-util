package com.tibco.as.util.convert.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tibco.as.util.log.LogFactory;

public class ObjectToBlob extends AbstractConverter<Object, byte[]> {

	private Logger log = LogFactory.getLog(ObjectToBlob.class);

	@Override
	protected byte[] doConvert(Object source) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(source);
			return baos.toByteArray();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Could not serialize object", e);
		}
		return null;
	}

}
