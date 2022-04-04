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

	public List<SeasonPoll> filterByPollName(List<SeasonPoll> polls, String name) {
		List<SeasonPoll> filtered = polls.stream().filter(p -> {
			return p.getPollName().equals(name);
		}).collect(Collectors.toList());
		return filtered;
	}

	public List<SeasonPoll> filterPollbyYearRange(List<SeasonPoll> polls, int startYear, int stopYear) {
		List<SeasonPoll> filtered = polls.stream().filter(p -> {
			int year = p.getYear();
			return year >= startYear && year <= stopYear;
		}).collect(Collectors.toList());
		return filtered;
	}

	public List<SeasonPoll> filterByPostSeason(List<SeasonPoll> polls) {

		List<SeasonPoll> filtered = polls.stream().filter(a -> a.getSeasonType().equals("postseason"))
				.collect(Collectors.toList());

		return filtered;
	}

	// scored by final ranking and number of Times in AP poll
	public void APConsistRankedInRange(int startYear, int stopYear) {
		List<SeasonPoll> filtered = data.getSeasonPolls();
		filtered = filterByPollName(filtered, "AP Top 25");
		filtered = filterPollbyYearRange(filtered, startYear, stopYear);
		filtered.sort((a, b) -> a.getYear() - b.getYear());
		filtered = filterByPostSeason(filtered);

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
		for(String school : map.keySet()) {
			Pair pair = new Pair(school, map.get(school));
			pairs.add(pair);
		}
		
		pairs.sort((a,b) -> b.getPoints() - a.getPoints());
		for(Pair pair : pairs) {
			System.out.println(pair);
		}

	}

	// Points starts being kept track on 2014
	public List<Pair> AP25MostPointsInRange(int startYear, int stopYear) {
		if (startYear < 2014) {
			System.err.println("Warning point totals are not kept before 2014");
		}
		List<SeasonPoll> polls = data.getSeasonPolls();

		List<SeasonPoll> filtered = filterByPollName(polls, "AP Top 25");
		filtered = filterPollbyYearRange(filtered, startYear, stopYear);

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

		pairs.sort((p1, p2) -> p2.getPoints() - p1.getPoints());

		System.out.println(" --- AP Total Points:  " + startYear + " -> " + stopYear);

		for (int i = 0; i < 5; ++i) {
			System.out.printf("%s : %d\n", pairs.get(i).getSchool(), pairs.get(i).getPoints());
		}

		return pairs;
	}

	public void displayPolls() {
		List<SeasonPoll> polls = data.getSeasonPolls();
		for (SeasonPoll poll : polls) {
			System.out.println(poll);
		}
	}

}
