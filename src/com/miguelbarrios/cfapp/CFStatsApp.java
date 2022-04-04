package com.miguelbarrios.cfapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.data_collection.drivers.DataLoader;
import com.miguelbarrios.data_collection.drivers.DataLoaderFile;
import com.miguelbarrios.enteties.SeasonPoll;

public class CFStatsApp {
	public static void main(String[] args) {
		
		DataLoader loader = new DataLoaderFile();
		loader.loadData();
		
//		
//		List<HistoricalPolls> histPolls = loader.getHistoricalPolls();
//		List<SeasonPoll> seasonPolls = new ArrayList<>();
//		
//		for(HistoricalPolls histPoll : histPolls) {
//			List<SeasonPoll> polls = SeasonPoll.convertHistPollsToSeasonPoll(histPoll);
//			seasonPolls.addAll(polls);
//		}
//		
//		List<SeasonPoll> filtered = seasonPolls.stream().filter( p -> {
//			return p.getPollName().equals("AP Top 25");
//		}).collect(Collectors.toList());
//		
//		filtered.sort((p1,p2) -> {
//			return p1.getYear() - p2.getYear();
//		});
//		
//		for(SeasonPoll cur : filtered) {
//			System.out.println(cur);
//		}
	}
}
