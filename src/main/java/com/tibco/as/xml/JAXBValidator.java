package com.tibco.as.xml;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.util.ValidationEventCollector;

public class JAXBValidator extends ValidationEventCollector {

	private final static String MESSAGE = "{0} (line #{1}, column #{2})";

	private Logger logger = Logger.getLogger(JAXBValidator.class.getName());

	@Override
	public boolean handleEvent(ValidationEvent event) {
		int severity = event.getSeverity();
		if (severity == ValidationEvent.WARNING
				|| severity == ValidationEvent.ERROR
				|| severity == ValidationEvent.FATAL_ERROR) {
			ValidationEventLocator locator = event.getLocator();
			String message;
			if (locator == null) {
				message = event.getMessage();
			} else {
				message = MessageFormat.format(MESSAGE, event.getMessage(),
						locator.getLineNumber(), locator.getColumnNumber());
			}
			logger.log(getLevel(severity), message, event.getLinkedException());
		}
		return true;
	}

	private Level getLevel(int severity) {
		switch (severity) {
		case ValidationEvent.WARNING:
			return Level.WARNING;
		case ValidationEvent.ERROR:
		case ValidationEvent.FATAL_ERROR:
			return Level.SEVERE;
		default:
			return Level.OFF;
		}
	}
}