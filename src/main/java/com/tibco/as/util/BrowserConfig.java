package com.tibco.as.util;

import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.DistributionScope;
import com.tibco.as.space.browser.BrowserDef.TimeScope;

public class BrowserConfig {

	private BrowserType browserType;
	private TimeScope timeScope;
	private DistributionScope distributionScope;
	private Long timeout;
	private Long prefetch;
	private Long queryLimit;
	private String filter;

	public void copyTo(BrowserConfig target) {
		if (target.browserType == null) {
			target.browserType = browserType;
		}
		if (target.distributionScope == null) {
			target.distributionScope = distributionScope;
		}
		if (target.filter == null) {
			target.filter = filter;
		}
		if (target.prefetch == null) {
			target.prefetch = prefetch;
		}
		if (target.queryLimit == null) {
			target.queryLimit = queryLimit;
		}
		if (target.timeout == null) {
			target.timeout = timeout;
		}
		if (target.timeScope == null) {
			target.timeScope = timeScope;
		}
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public void setBrowserType(BrowserType browserType) {
		this.browserType = browserType;
	}

	public TimeScope getTimeScope() {
		return timeScope;
	}

	public void setTimeScope(TimeScope timeScope) {
		this.timeScope = timeScope;
	}

	public DistributionScope getDistributionScope() {
		return distributionScope;
	}

	public void setDistributionScope(DistributionScope distributionScope) {
		this.distributionScope = distributionScope;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public Long getPrefetch() {
		return prefetch;
	}

	public void setPrefetch(Long prefetch) {
		this.prefetch = prefetch;
	}

	public Long getQueryLimit() {
		return queryLimit;
	}

	public void setQueryLimit(Long queryLimit) {
		this.queryLimit = queryLimit;
	}

	public BrowserDef getBrowserDef() {
		BrowserDef browserDef = BrowserDef.create();
		if (timeScope != null) {
			browserDef.setTimeScope(timeScope);
		}
		if (distributionScope != null) {
			browserDef.setDistributionScope(distributionScope);
		}
		if (timeout != null) {
			browserDef.setTimeout(timeout);
		}
		if (prefetch != null) {
			browserDef.setPrefetch(prefetch);
		}
		if (queryLimit != null) {
			if (Utils.hasMethod(BrowserDef.class, "setQueryLimit")) {
				browserDef.setQueryLimit(queryLimit);
			}
		}
		return browserDef;
	}
}
