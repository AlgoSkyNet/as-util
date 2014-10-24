package com.tibco.as.log;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.FileLogOptions;

public class LogFactory {

	public static Logger configure(LogLevel level, String filePath,
			Integer fileLimit, int fileCount, boolean fileAppend,
			LogLevel fileLevel) throws SecurityException, IOException {
		Logger logger = getRootLogger(level);
		if (filePath != null) {
			FileHandler fileHandler = getFileHandler(filePath, fileLimit,
					fileCount, fileAppend);
			fileHandler.setLevel(fileLevel.getLevel());
			logger.addHandler(fileHandler);
			FileLogOptions options = FileLogOptions.create();
			options.setAppend(fileAppend);
			options.setFileCount(fileCount);
			if (fileLimit != null) {
				options.setLimit(fileLimit);
			}
			options.setFile(new File(filePath));
			options.setLogLevel(com.tibco.as.space.LogLevel.valueOf(fileLevel
					.getLevel().getName()));
			ASCommon.setFileLogging(options);
		}
		return logger;
	}

	private static FileHandler getFileHandler(String pattern, Integer limit,
			int count, Boolean append) throws SecurityException, IOException {
		if (pattern == null) {
			return new FileHandler();
		}
		if (limit == null) {
			if (append == null) {
				return new FileHandler(pattern);
			}
			return new FileHandler(pattern, append);
		}
		if (append == null) {
			return new FileHandler(pattern, limit, count);
		}
		return new FileHandler(pattern, limit, count, append);
	}

	public static Logger getLog(Class<?> clazz) {
		return Logger.getLogger(clazz.getName());
	}

	public static Logger getRootLogger(LogLevel level) {
		System.setProperty("java.util.logging.SimpleFormatter.format",
				"[%1$tFT%1$tT.%1$tL][%4$s] %5$s %6$s%n");
		// Get the root logger
		Logger rootLogger = Logger.getLogger("");
		for (Handler handler : rootLogger.getHandlers()) {
			handler.setLevel(level.getLevel());
		}
		rootLogger.setLevel(level.getLevel());
		return rootLogger;
	}

}
