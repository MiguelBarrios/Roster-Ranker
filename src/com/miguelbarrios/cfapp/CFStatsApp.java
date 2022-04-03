package com.miguelbarrios.cfapp;

import java.util.Collections;
import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.api_data_collection.gson_entities.Poll;
import com.miguelbarrios.data_collection.drivers.DataLoaderAPI;

public class CFStatsApp {
	public static void main(String[] args) {

		
//		dataManager.loadDataFromFiles();
//		
//		
//
//		List<HistoricalPolls> polls = dataManager.getPollsInRange(2010, 2021);
//		Collections.sort(polls, (p1,p2) -> {
//			return p1.getSeason() - p2.getSeason();
//		});
//		for(HistoricalPolls poll : polls) {
//			System.out.println(poll);
//		}
		
//		// Filter Polls by year
//		List<HistoricalPolls> historicalPolls = dataManager.filterHistoricalPolls("2021", (poll, year) -> {
//			return poll.getSeason() == Integer.parseInt(year);
//		});
		
//		for(HistoricalPolls poll : historicalPolls) {
//			System.out.println(poll);
//		}
//		
//		
//		// Filter by pollName
//		List<Poll> polls = dataManager.filterPolls("AP Top 25", (Poll p, String pollName) -> {
//			return p.getPoll().equalsIgnoreCase(pollName);
//		});
//		
//		for(Poll poll : polls) {
//			System.out.println(poll);
//		}
		
//		
//		System.out.println("EOP");
		
		
	}
}
