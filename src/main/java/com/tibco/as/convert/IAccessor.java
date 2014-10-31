package com.tibco.as.convert;

/**
 * Abstracts the notion of a field. Defines a strategy for accessing the value
 * of an object.
 * 
 * @author Julien Ruaux
 */
public interface IAccessor {

	public Object get(Object object);

	public Object set(Object object, Object value);
}
