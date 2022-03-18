package com.miguelbarrios.cfpentities;

public class HistoricalPolls {
	private int season;
	
	private String seasonType;
	
	private int week;
	
	private Poll[] polls;

	public HistoricalPolls(int season, String seasonType, int week, Poll[] polls) {
		super();
		this.season = season;
		this.seasonType = seasonType;
		this.week = week;
		this.polls = polls;
	}

	public int getSeason() {
		return season;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public int getWeek() {
		return week;
	}

	public Poll[] getPolls() {
		return polls;
	}
	
	
	
	
	
	
}
