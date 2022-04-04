package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.api_data_collection.gson_entities.Poll;
import com.miguelbarrios.enteties.SeasonPoll;


public class Data {
	
	private List<SeasonPoll> seasonPolls;
	
	private List<HistoricalPolls> historicalPolls;

	public Data() {
		historicalPolls = new ArrayList<>();
		seasonPolls = new ArrayList<>();
	}
	
	public void addSeasonalPoll(SeasonPoll poll) {
		seasonPolls.add(poll);
	}
	
	public void addPolls(List<HistoricalPolls> polls) {
		historicalPolls.addAll(polls);
	}
	
	public List<HistoricalPolls> getHistPolls() {
		List<HistoricalPolls> res = new ArrayList<>(historicalPolls);
		return res;
	}
	
	public List<HistoricalPolls> filterHistoricalPolls(String str, BiPredicate<HistoricalPolls, String> matcher) {
		List<HistoricalPolls> filtered = new ArrayList<>();
		for (HistoricalPolls p : historicalPolls) {
			if (matcher.test(p, str)) {
				filtered.add(p);
			}
		}
		return filtered;
	}
	
	public List<HistoricalPolls> getPollsInRange(int startYear, int stopYear){
		
		List<HistoricalPolls> filtered = new ArrayList<>();
		for(HistoricalPolls p : historicalPolls) {
			int year = p.getSeason();
			if(year >= startYear && year <= stopYear) {
				filtered.add(p);
			}
		}
		return filtered;
	}
	
	public List<Poll> filterPolls(String string, BiPredicate<Poll, String> matcher) {
		List<Poll> filtered = new ArrayList<>();
		for (HistoricalPolls p : historicalPolls) {
			for (Poll poll : p.getPolls()) {
				if (matcher.test(poll, string)) {
					filtered.add(poll);
				}
			}
		}

		return filtered;
	}
	
}
