package com.tibco.as.util.file;

import java.io.File;

public class FileUtils {

	public static final char EXTENSION_SEPARATOR = '.';

	public static String getBaseName(File file) {
		if (file == null) {
			return null;
		}
		return getBaseName(file.getName());
	}

	public static String getBaseName(String filename) {
		if (filename == null) {
			return null;
		}
		int position = getExtensionPosition(filename);
		if (position == -1) {
			return filename;
		}
		return filename.substring(0, position);
	}

	private static int getExtensionPosition(String filename) {
		return filename.lastIndexOf(EXTENSION_SEPARATOR);
	}

	public static String getExtension(File file) {
		if (file == null) {
			return null;
		}
		return getExtension(file.getName());
	}

	public static String getExtension(String filename) {
		if (filename == null) {
			return null;
		}
		if (filename.length() <= 1) {
			return "";
		}
		int position = getExtensionPosition(filename);
		if (position == -1) {
			return "";
		}
		return filename.substring(position + 1);
	}
}
