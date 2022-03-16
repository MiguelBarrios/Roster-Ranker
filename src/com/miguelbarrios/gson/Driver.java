package com.miguelbarrios.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miguelbarrios.requests.RequestBuilder;
import com.miguelbarrios.requests.RequestHandler;

public class Driver {

	public static void main(String[] args) {
		RequestHandler client = new RequestHandler();
		RequestBuilder requests = new RequestBuilder();
		
		String request = requests.regularSeasonPollRequest(2021, 1);
		String response = client.sendGetRequest(request);
		System.out.println(response);
		deserializeHistPollRankings(response);
		
	}
	
	public static void deserializeHistPollRankings(String json) {
		Gson gson = new Gson();
		//HistPollRanking[] rankings = new Gson().fromJson(json, HistPollRanking[].class);
		HistPollRanking[] rankings = gson.fromJson(json, HistPollRanking[].class);
		System.out.println("tests");
	}
	
	
	
}
