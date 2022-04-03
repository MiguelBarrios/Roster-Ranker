package com.miguelbarrios.api_data_collection.gson_entities;

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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( seasonType + " Season: " + season + ", week: " + week);
		sb.append("\n");
		for(Poll poll : polls) {
			sb.append("\t").append(poll).append("\n");
		}
		return sb.toString();
	}
	
}
