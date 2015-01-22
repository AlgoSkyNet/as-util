package com.tibco.as.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;

import com.tibco.as.util.Utils;

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
	

	public static File copy(String resource, File dir) throws IOException {
		return copy(resource, dir, resource);
	}

	public static File createTempDirectory() throws IOException {
		File dir = File.createTempFile(Utils.class.getName(),
				String.valueOf(System.currentTimeMillis()));
		if (!dir.delete()) {
			throw new IOException(MessageFormat.format(
					"Could not delete temp file: {0}", dir.getAbsolutePath()));
		}
		if (!dir.mkdir()) {
			throw new IOException(MessageFormat.format(
					"Could not create temp directory: {0}",
					dir.getAbsolutePath()));
		}
		return dir;
	}

	public static File copy(String resource, File dir, String filename)
			throws IOException {
		File file = new File(dir, filename);
		copyToFile(resource, file);
		return file;
	}

	public static void copyToFile(String resource, File file)
			throws IOException {
		OutputStream out = new FileOutputStream(file);
		try {
			InputStream in = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(resource);
			if (in == null) {
				throw new FileNotFoundException(resource);
			}
			byte[] buff = new byte[4096];
			int count;
			try {
				while ((count = in.read(buff)) != -1) {
					if (count > 0) {
						out.write(buff, 0, count);
					}
				}
			} finally {
				in.close();
			}
		} finally {
			out.close();
		}
	}
}
