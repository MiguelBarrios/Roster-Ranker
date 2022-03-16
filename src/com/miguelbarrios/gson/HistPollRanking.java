package com.miguelbarrios.gson;

public class HistPollRanking {
	private int season;
	
	private String seasonType;
	
	private int week;
	
	private Poll[] polls;

	public HistPollRanking(int season, String seasonType, int week, Poll[] polls) {
		super();
		this.season = season;
		this.seasonType = seasonType;
		this.week = week;
		this.polls = polls;
	}
	
	
	
	
}
