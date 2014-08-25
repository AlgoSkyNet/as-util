package com.tibco.as.accessors;

/**
 * Abstracts the notion of a field. Defines a strategy for accessing the value
 * of an object.
 * 
 * @author Julien Ruaux
 */
public interface IAccessor<T> {

	public Object get(T object);

	public Object set(T object, Object value);
}
