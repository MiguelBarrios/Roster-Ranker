package com.miguelbarrios.cfpentities;

import java.util.List;
import java.util.Map;

public class StatGenerator {
	
	private Data data;
	
	public StatGenerator(Data data) {
		this.data = data;
	}
	
	public void inRangeAllTimeWinner(String pollName, int startYear, int stopYear) {
		List<Poll> polls = data.getAllPolls("AP Top 25", 2019, 2021);
		for(Poll poll : polls) {
			poll.displayPoll();
		}
	}
}
