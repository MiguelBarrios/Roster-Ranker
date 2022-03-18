package com.miguelbarrios.interfaces;

import com.miguelbarrios.cfpentities.Poll;

public interface PollMatcher {
	  boolean matches(Poll pres, String string);
}
