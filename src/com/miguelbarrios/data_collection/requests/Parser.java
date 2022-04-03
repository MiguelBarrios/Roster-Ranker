package com.miguelbarrios.data_collection.requests;

import com.google.gson.Gson;
import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;

public class Parser {
		
	private Gson deserializer;
	
	public Parser() {
		deserializer = new Gson();
	}
	
	public HistoricalPolls parseHistoricalPollJson(String json) {
		HistoricalPolls[] rankings = deserializer.fromJson(json, HistoricalPolls[].class);
		if(rankings.length == 0)
			return null;
		return rankings[0];
	}
	
}
