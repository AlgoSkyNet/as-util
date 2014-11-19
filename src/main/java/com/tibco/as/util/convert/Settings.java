package com.tibco.as.util.convert;

import java.util.TimeZone;

public class Settings {

	private final static String DEFAULT_PATTERN_BOOLEAN_TRUE = "true";
	private final static String DEFAULT_PATTERN_BOOLEAN_FALSE = "false";
	private final static String DEFAULT_TIMEZONE = "GMT";

	private Blob blob;
	private String booleanTruePattern;
	private String booleanFalsePattern;
	private String datePattern;
	private String timeZoneID;
	private String numberPattern;

	public void copyTo(Settings target) {
		target.blob = blob;
		target.booleanTruePattern = booleanTruePattern;
		target.booleanFalsePattern = booleanFalsePattern;
		target.datePattern = datePattern;
		target.numberPattern = numberPattern;
		target.timeZoneID = timeZoneID;
	}

	public Blob getBlob() {
		if (blob == null) {
			return Blob.HEX;
		}
		return blob;
	}

	public void setBlob(Blob blobFormat) {
		this.blob = blobFormat;
	}

	public String getBooleanTruePattern() {
		if (booleanTruePattern == null) {
			return DEFAULT_PATTERN_BOOLEAN_TRUE;
		}
		return booleanTruePattern;
	}

	public void setBooleanTruePattern(String pattern) {
		this.booleanTruePattern = pattern;
	}

	public String getBooleanFalsePattern() {
		if (booleanFalsePattern == null) {
			return DEFAULT_PATTERN_BOOLEAN_FALSE;
		}
		return booleanFalsePattern;
	}

	public void setBooleanFalsePattern(String pattern) {
		this.booleanFalsePattern = pattern;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String pattern) {
		this.datePattern = pattern;
	}

	public String getTimeZoneID() {
		if (timeZoneID == null) {
			return DEFAULT_TIMEZONE;
		}
		return timeZoneID;
	}

	public void setTimeZoneID(String timeZoneID) {
		this.timeZoneID = timeZoneID;
	}

	public String getNumberPattern() {
		return numberPattern;
	}

	public void setNumberPattern(String pattern) {
		this.numberPattern = pattern;
	}

	public TimeZone getTimeZone() {
		String timeZoneID = getTimeZoneID();
		if (timeZoneID == null) {
			return null;
		}
		return TimeZone.getTimeZone(timeZoneID);
	}

}
