package com.tibco.as.convert;

import java.util.TimeZone;

public class ConversionConfig implements Cloneable {

	private final static String DEFAULT_PATTERN_BOOLEAN_TRUE = "true";
	private final static String DEFAULT_PATTERN_BOOLEAN_FALSE = "false";
	private final static String DEFAULT_TIMEZONE = "GMT";

	private Blob blob;
	private String booleanTruePattern;
	private String booleanFalsePattern;
	private String datePattern;
	private String timeZoneID;
	private String numberPattern;
	private ConversionConfig defaults;

	@Override
	public ConversionConfig clone() {
		ConversionConfig clone = new ConversionConfig();
		copyTo(clone);
		return clone;
	}

	public void copyTo(ConversionConfig target) {
		target.blob = blob;
		target.booleanTruePattern = booleanTruePattern;
		target.booleanFalsePattern = booleanFalsePattern;
		target.datePattern = datePattern;
		target.numberPattern = numberPattern;
		target.timeZoneID = timeZoneID;
	}

	public Blob getBlob() {
		if (blob == null) {
			if (defaults == null) {
				return Blob.HEX;
			}
			return defaults.getBlob();
		}
		return blob;
	}

	public void setBlob(Blob blobFormat) {
		this.blob = blobFormat;
	}

	public String getBooleanTruePattern() {
		if (booleanTruePattern == null) {
			if (defaults == null) {
				return DEFAULT_PATTERN_BOOLEAN_TRUE;
			}
			return defaults.getBooleanTruePattern();
		}
		return booleanTruePattern;
	}

	public void setBooleanTruePattern(String pattern) {
		this.booleanTruePattern = pattern;
	}

	public String getBooleanFalsePattern() {
		if (booleanFalsePattern == null) {
			if (defaults == null) {
				return DEFAULT_PATTERN_BOOLEAN_FALSE;
			}
			return defaults.getBooleanFalsePattern();
		}
		return booleanFalsePattern;
	}

	public void setBooleanFalsePattern(String pattern) {
		this.booleanFalsePattern = pattern;
	}

	public String getDatePattern() {
		if (datePattern == null) {
			if (defaults == null) {
				return null;
			}
			return defaults.getDatePattern();
		}
		return datePattern;
	}

	public void setDatePattern(String pattern) {
		this.datePattern = pattern;
	}

	public String getTimeZoneID() {
		if (timeZoneID == null) {
			if (defaults == null) {
				return DEFAULT_TIMEZONE;
			}
			return defaults.getTimeZoneID();
		}
		return timeZoneID;
	}

	public void setTimeZoneID(String timeZoneID) {
		this.timeZoneID = timeZoneID;
	}

	public String getNumberPattern() {
		if (numberPattern == null) {
			if (defaults == null) {
				return null;
			}
			return defaults.getNumberPattern();
		}
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

	public void setDefaults(ConversionConfig defaults) {
		this.defaults = defaults;
	}

}
