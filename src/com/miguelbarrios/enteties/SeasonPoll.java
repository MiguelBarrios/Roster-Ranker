package com.miguelbarrios.enteties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.api_data_collection.gson_entities.Poll;
import com.miguelbarrios.api_data_collection.gson_entities.Rank;

public class SeasonPoll {
	
	private String pollName;
	
	private String seasonType;

	private int year;
	
	private int week;
	
	private Rank[] ranks;

	public SeasonPoll(String pollName, String seasonType, int year, int week, Rank[] ranks) {
		super();
		this.pollName = pollName;
		this.seasonType = seasonType;
		this.year = year;
		this.week = week;
		this.ranks = ranks;
	}

	public static List<SeasonPoll> convertHistPollsToSeasonPoll(HistoricalPolls histPolls) {
		
		List<SeasonPoll> polls = new ArrayList<>();
		if(histPolls == null)
			return polls;
		
		for(Poll poll : histPolls.getPolls()) {
			SeasonPoll seasonPoll = new SeasonPoll(poll.getPoll(), histPolls.getSeasonType()
					, histPolls.getSeason(), histPolls.getWeek(), poll.getRanks());
			polls.add(seasonPoll);
		}
		
		return polls;
	}

	@Override
	public String toString() {
		return  year + " " + pollName + ", seasonType=" + seasonType  + ", week=" + week
				+ ", ranks=" + Arrays.toString(ranks) + "]";
	}

	public String getPollName() {
		return pollName;
	}

	public void setPollName(String pollName) {
		this.pollName = pollName;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public Rank[] getRanks() {
		return ranks;
	}

	public void setRanks(Rank[] ranks) {
		this.ranks = ranks;
	}
	
	
	
	
	
}
