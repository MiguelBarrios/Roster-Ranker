package com.miguelbarrios.interfaces;

import com.miguelbarrios.cfpentities.HistoricalPolls;

public interface HistoricalPollMatcher {
	  boolean matches(HistoricalPolls pres, String string);
}
