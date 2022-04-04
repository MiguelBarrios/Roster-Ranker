package com.miguelbarrios.cfapp;

import java.util.List;
import java.util.stream.Collectors;

import com.miguelbarrios.cfpentities.StatGenerator;
import com.miguelbarrios.data_collection.drivers.DataLoader;
import com.miguelbarrios.data_collection.drivers.DataLoaderFile;
import com.miguelbarrios.enteties.SeasonPoll;

public class CFStatsApp {
	public static void main(String[] args) {
		
		DataLoader loader = new DataLoaderFile();
		loader.loadData();
		
		StatGenerator stats = new StatGenerator(loader.getData());
		
		//stats.AP25MostPointsInRange(2001, 2021);
		stats.APConsistRankedInRange(1980,2021);
		
//		List<SeasonPoll> polls = loader.getData().getSeasonPolls();
//	
//		List<SeasonPoll> filtered = polls.stream().filter(p -> p.getPollName().equals("AP Top 25")).collect(Collectors.toList());
//		filtered.sort((p1,p2) -> p1.getYear() - p2.getYear());
//		for(SeasonPoll poll : filtered) {
//			System.out.println(poll);
//		}
		
	}
	
}
