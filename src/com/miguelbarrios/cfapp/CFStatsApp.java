package com.miguelbarrios.cfapp;

import com.miguelbarrios.cfpentities.Data;
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
		
		for(int i = 1; i <= 15; ++i ) {
			String requestAPPoll = requestBuilder.buildPollRequest(2021, i);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			parser.rankingParser(response);
		}

		
		
		Data.displayPolls();
	}
	
}
