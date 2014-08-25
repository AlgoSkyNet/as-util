package com.tibco.as.convert;

import com.tibco.as.space.FieldDef.FieldType;

public class Adapter {

	public static <T extends Enum<?>> String print(T value) {
		return value == null ? null : value.name().toLowerCase();
	}

	public static String parse(String name) {
		return name.toUpperCase();
	}

	public static Class<?> parseClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static String printClass(Class<?> clazz) {
		return clazz.getName();
	}

	public static String printFieldType(FieldType type) {
		return print(type);
	}

	public static FieldType parseFieldType(String name) {
		return FieldType.valueOf(parse(name));
	}

}
