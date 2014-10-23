package com.tibco.as.convert;

/**
 * Converter for a Java type into another Java type.
 * 
 * @author Julien Ruaux
 * 
 */
public interface IConverter {

	/**
	 * Converts given object
	 */
	Object convert(Object source) throws Exception;

}
