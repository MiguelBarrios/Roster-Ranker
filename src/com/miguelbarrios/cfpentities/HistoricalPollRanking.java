package com.miguelbarrios.cfpentities;

import java.util.List;

public class HistoricalPollRanking {
	

	
	private List<PollItem> polls;
	
	class PollItem{
		private int season;
		
		private String seasonType;
		
		private int week;
	}

	public HistoricalPollRanking(int season, String seasonType, int week) {
		super();

	}
	
	
}
