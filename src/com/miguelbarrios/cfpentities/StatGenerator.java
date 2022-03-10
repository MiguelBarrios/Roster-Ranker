package com.miguelbarrios.cfpentities;

import java.util.List;
import java.util.Map;

public class StatGenerator {
	
	
	public void inRangeAllTimeWinner(int startYear, int endYear) {
		Map<String, List<Poll>> data = Data.getPollData();
		
		for(String pollName : data.keySet()) {
			List<Poll> polls = data.get(pollName);
			for(Poll poll : polls) {
				System.out.println(String.format("%s season: %d week: %d ", pollName, poll.getSeason(), poll.getWeek()));
			}
		}
	}
}
