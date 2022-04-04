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

	public StatGenerator(Data data) {
		this.data = data;
	}
	
	public void numberOfAppearanceInPoll(String pollName, int startYear, int stopYear, boolean postSeasonOnly) {
		List<SeasonPoll> polls = data.getSeasonPolls();
		List<SeasonPoll> filtered = filterByPollNameAndYearRange(polls, pollName, startYear, stopYear);
		
		if(postSeasonOnly) {
			filtered = filtered.stream()
					.filter(p -> p.getSeasonType().equals("postseason")).collect(Collectors.toList());
		}
		
		Map<String, Integer> map = new HashMap<>();
		for(SeasonPoll poll : filtered) {
			for(Rank rank : poll.getRanks()) {
				String school = rank.getSchool();
				if(map.containsKey(school)) {
					map.put(school, map.get(school) + 1);
				}else {
					map.put(school, 1);
				}
			}
		}
		
		List<String> schools = new ArrayList<>(map.keySet());
		schools.sort((a,b) -> map.get(b) - map.get(a) );
		for(String school : schools) {
			System.out.println(school + " " + map.get(school));
		}
	}
	
	public List<SeasonPoll> filterByPollNameAndYearRange(List<SeasonPoll> polls, String pollName, int startYear, int stopYear){
		List<SeasonPoll> filtered = polls.stream()
				.filter(p -> p.getPollName().equals(pollName))
				.filter(p -> {
					return p.getYear() >= startYear && p.getYear() <= stopYear;
				}).collect(Collectors.toList());
		return filtered;
	}

	// scored by final ranking and number of Times in AP poll
	public void APConsistRankedInRange(int startYear, int stopYear) {
		
		List<SeasonPoll> polls = data.getSeasonPolls();
		List<SeasonPoll> filtered = filterByPollNameAndYearRange(polls, "AP Top 25", startYear, stopYear);
		
		filtered = filtered.stream()
				.filter(p -> p.getSeasonType().equals("postseason"))
				.collect(Collectors.toList());
		
		Map<String, Integer> map = new HashMap<>();
		for (SeasonPoll poll : filtered) {
			for (Rank rank : poll.getRanks()) {
				String school = rank.getSchool();
				int finalRank = rank.getRank();
				int points = 26 - finalRank;
				if (map.containsKey(school)) {
					map.put(school, map.get(school) + points);
				} else {
					map.put(school, points);
				}
			}
		}
		
		List<Pair> pairs = new ArrayList<>();
		for(String school : map.keySet())
			pairs.add(new Pair(school, map.get(school)));
		
		pairs.sort((a,b) -> b.getPoints() - a.getPoints());
		pairs.forEach(System.out::println);
	}

	// Points starts being kept track on 2014
	public List<Pair> AP25MostPointsInRange(int startYear, int stopYear) {
		if (startYear < 2014) {
			System.err.println("Warning point totals are not kept before 2014");
		}
		
		List<SeasonPoll> polls = data.getSeasonPolls();
		List<SeasonPoll> filtered = filterByPollNameAndYearRange(polls, "AP Top 25", startYear, stopYear);

		Map<String, Integer> map = new HashMap<>();
		for (SeasonPoll poll : filtered) {
			for (Rank rank : poll.getRanks()) {
				String school = rank.getSchool();
				int points = rank.getPoints();
				if (map.containsKey(school)) {
					map.put(school, map.get(school) + points);
				} else {
					map.put(school, points);
				}
			}
		}

		List<Pair> pairs = new ArrayList<>();
		for (String school : map.keySet()) {
			pairs.add(new Pair(school, map.get(school)));
		}

		System.out.println(" --- AP Total Points:  " + startYear + " -> " + stopYear);
		pairs.sort((p1, p2) -> p2.getPoints() - p1.getPoints());
		for (int i = 0; i < 5; ++i) {
			System.out.printf("%s : %d\n", pairs.get(i).getSchool(), pairs.get(i).getPoints());
		}

		return pairs;
	}


}
