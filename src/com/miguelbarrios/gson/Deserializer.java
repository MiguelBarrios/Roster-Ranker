package com.miguelbarrios.gson;

import com.google.gson.Gson;

public class Deserializer {

	private Gson gson;
	
	public Deserializer() {
		gson = new Gson();
	}
	
	public HistPollRanking deserializeHistPolRankings(String json){
		HistPollRanking[] rankings = gson.fromJson(json, HistPollRanking[].class);
		return rankings[0];
	}
}
