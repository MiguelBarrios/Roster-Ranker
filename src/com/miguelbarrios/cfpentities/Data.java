package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.enteties.SeasonPoll;


public class Data {
	
	private List<SeasonPoll> seasonPolls;
	
	private List<HistoricalPolls> historicalPolls;

	public Data() {
		historicalPolls = new ArrayList<>();
		seasonPolls = new ArrayList<>();
	}
	
	public List<SeasonPoll> getSeasonPolls(){
		return new ArrayList(seasonPolls);
	}
	
	public void addSeasonalPolls(List<SeasonPoll> poll) {
		seasonPolls.addAll(poll);
	}
	
	public void addPolls(List<HistoricalPolls> polls) {
		historicalPolls.addAll(polls);
	}
}
