package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.miguelbarrios.api_data_collection.gson_entities.Rank;
import com.miguelbarrios.enteties.Pair;
import com.miguelbarrios.enteties.SeasonPoll;

public class StatGenerator {
	
	private Data data;
	
	public StatGenerator(Data data){
		this.data = data;
	}
	
	public void APMostPointsInRange(int startYear, int stopYear) {
		List<SeasonPoll> polls = data.getSeasonPolls();
		
		// Filter poll by name
		List<SeasonPoll> filtered = polls.stream().filter(p -> {
			if(p.getPollName().equals("AP Top 25")) {
				int year = p.getYear();
				return year >= startYear && year <= stopYear;
			}
			return false;
		}).collect(Collectors.toList());
		
		filtered.sort((p1,p2) -> p1.getYear() -  p2.getYear());
		
		Map<String, Integer> map = new HashMap<>();
		for(SeasonPoll poll : filtered) {
			for(Rank rank : poll.getRanks()) {
				String school = rank.getSchool();
				int points = rank.getPoints();
				if(map.containsKey(school)) {
					map.put(school, map.get(school) + points);
				}
				else {
					map.put(school, points);
				}
			}
		}
		
		List<Pair> pairs = new ArrayList<>();
		for(String school : map.keySet()) {
			pairs.add( new Pair(school, map.get(school)));
		}
		
		pairs.sort((p1,p2) -> p2.getPoints() - p1.getPoints());
		for(Pair pair : pairs) {
			System.out.println(pair);
		}
		
		
		
		
	}
	
}
