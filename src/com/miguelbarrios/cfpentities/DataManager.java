package com.miguelbarrios.cfpentities;

import java.util.ArrayList;

import com.miguelbarrios.gson.Deserializer;
import com.miguelbarrios.gson.HistPollRanking;
import com.miguelbarrios.requests.RequestBuilder;
import com.miguelbarrios.requests.RequestHandler;
import com.miguelbarrios.requests.RequestParser;

public class DataManager {
	private RequestBuilder requestBuilder;
	
	private RequestHandler requestHandler;
	
	private RequestParser  requestParser;
	
	private Data data;
	
	public DataManager() {
		this.requestBuilder = new RequestBuilder();
		this.requestHandler = new RequestHandler();
		this.requestParser = new RequestParser();
	}
	
	public void loadDataFromAPI() {
		
	}
	
	public void getCFPHistPollBySeason() {
		
	}
	
	public void loadDataFromFiles() {
		
	}
	
	public ArrayList<HistPollRanking> getHistoricalPollsForSeason(int year) {
		ArrayList<HistPollRanking> polls = new ArrayList<>();
		Deserializer ds = new Deserializer();
		// Get polls for each week of the reqular season
		for(int week = 1; week <= 15; ++week ) {
			String requestAPPoll = requestBuilder.regularSeasonPollRequest(year, week);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			HistPollRanking ranking = requestParser.parseHistoricalPoll(response);
			polls.add(ranking);
		}
		
		return polls;
		
//		// get post season polls
//		String postSeasonPollRequest = requestBuilder.postSeasonPollRequest(year);
//		String response = requestHandler.sendGetRequest(postSeasonPollRequest);
//		parser.rankingParser(response, false, this);
	}
	
	
	
	

}
