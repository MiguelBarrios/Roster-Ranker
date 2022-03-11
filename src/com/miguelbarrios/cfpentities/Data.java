package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miguelbarrios.requests.RequestBuilder;
import com.miguelbarrios.requests.RequestHandler;
import com.miguelbarrios.requests.RequestParser;

public class Data {
	
	private Map<String, List<Poll>> polls;
	
	private RequestBuilder requestBuilder;
	
	private RequestHandler requestHandler;
	
	private RequestParser parser;
	
	public Data() {
		requestBuilder = new RequestBuilder();
		requestHandler = new RequestHandler();
		parser = new RequestParser();
		polls = new HashMap<>();
	}

	public void addPoll(Poll poll) {
		if (!polls.containsKey(poll.getName())) {
			polls.put(poll.getName(), new ArrayList<Poll>());
		} 
		
		polls.get(poll.getName()).add(poll);
	}
	
	public Map<String, List<Poll>> getPollData(){
		return polls;
	}
	
	public void displayPolls() {
		
		for(String pollName : polls.keySet()) {
			List<Poll> content = polls.get(pollName);

			for(Poll poll: content) {
				poll.displayPoll();
			}
		}
		
	}
	
	public void loadSeasonData(int year) {
		
		// Get polls for each week of the reqular season
		int seasonStart = 1;
		int seasonEnd = 15;
		for(int week = seasonStart; week <= seasonEnd; ++week ) {
			String requestAPPoll = requestBuilder.regularSeasonPollRequest(year, week);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			try {
				parser.rankingParser(response, true, this);				
			}
			catch(Exception e){
				e.printStackTrace();
				System.err.println("Error poll for year: " + year + " week: " + week);
				System.err.println(response);
			}
		}
		
		// get post season polls
		String postSeasonPollRequest = requestBuilder.postSeasonPollRequest(year);
		String response = requestHandler.sendGetRequest(postSeasonPollRequest);
		parser.rankingParser(response, false, this);
	}
	
	public void loadData(int startYear, int finalYear) {
		
		for(int currentYear = startYear; currentYear <= finalYear; ++currentYear) {
			loadSeasonData(currentYear);
		}	
	}
	
	public List<Poll> getAllPolls(String pollName, int startYear, int stopYear) {
		List<Poll> res = new ArrayList<>();
		List<Poll> polls = this.polls.get(pollName);
		
		for(Poll poll : polls) {
			int year = poll.getSeason();
			if(year >= startYear && year <= stopYear) {
				res.add(poll);
			}
		}
		
		return res;

	}
	
	
	
	
}
