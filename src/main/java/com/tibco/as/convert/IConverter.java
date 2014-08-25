package com.tibco.as.convert;

/**
 * Converter for a Java type into another Java type.
 * 
 * @author Julien Ruaux
 * 
 */
public interface IConverter<S, T> {

	/**
	 * Converts given S to type T.
	 * 
	 * @param S
	 *            value
	 * @return converted T value
	 */
	T convert(S source) throws ConvertException;

}
