package com.miguelbarrios.cfpentities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatGenerator {
	
	private Data data;
	
	public StatGenerator(Data data) {
		this.data = data;
	}
	
	public void displayAllPolls() {
		data.displayPolls();
	}
	
	public void inRangeAllTimeWinner(String pollName, int startYear, int stopYear) {
		List<Poll> polls = data.getAllPolls("AP Top 25", 2019, 2021);
		for(Poll poll : polls) {
			poll.displayPoll();
			
			//methods to filer data, by type and in range complete 
		}
	}
	
	public Map<String, Integer> inRangeAllTimeFinalWinner(String pollName, int startYear, int stopYear) {
		List<Poll> polls = data.getAllPolls(pollName, startYear, stopYear);
		Map<String, Integer> map = new HashMap<>();
		
		
		for(Poll poll : polls) {
			
			if(poll.getWeek() == -1 && poll.getName().equals(pollName)) {
				for(PollItem item : poll.getItems()) {
					String school = item.getSchool();
					int points = item.getPoints();
					if(!map.containsKey(school)) {
						map.put(school, points);
					}
					else {
						map.put(school, map.get(school) + points);
					}
				}
			}
		}
		
		return map;
	}
}
