package com.miguelbarrios.cfapp;

import com.miguelbarrios.cfpentities.Data;
import com.miguelbarrios.cfpentities.StatGenerator;
import com.miguelbarrios.requests.RequestBuilder;
import com.miguelbarrios.requests.RequestHandler;
import com.miguelbarrios.requests.RequestParser;

public class CFStatsApp {
	public static void main(String[] args) {
		CFStatsApp app = new CFStatsApp();
		app.run();
	}
	
	public void run() {
		RequestBuilder requestBuilder = new RequestBuilder();
		RequestHandler requestHandler = new RequestHandler();
		RequestParser parser = new RequestParser();
		StatGenerator stats = new StatGenerator();
		
		int seasonLength = 15;
		
		// Get polls for each week of the reqular season
		for(int i = 15; i <= seasonLength; ++i ) {
			String requestAPPoll = requestBuilder.regularSeasonPollRequest(2021, i);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			parser.rankingParser(response, true);
		}
		
		// get post season polls
		String postSeasonPollRequest = requestBuilder.postSeasonPollRequest(2021);
		String response = requestHandler.sendGetRequest(postSeasonPollRequest);
		parser.rankingParser(response, false);

		Data.displayPolls();
		stats.inRangeAllTimeWinner(0, 0);
		
		
	}
	
}
