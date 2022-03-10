package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
	public static Map<String, List<Poll>> polls = new HashMap<>();

	public static void addPoll(Poll poll) {
		if (!polls.containsKey(poll.getName())) {
			polls.put(poll.getName(), new ArrayList<Poll>());
		} 
		
		polls.get(poll.getName()).add(poll);
	}
	
	public static void displayPolls() {
		
		for(String pollName : polls.keySet()) {
			List<Poll> content = polls.get(pollName);
			for(Poll poll: content) {
				poll.displayPoll();
			}
		}
		
	}
}
