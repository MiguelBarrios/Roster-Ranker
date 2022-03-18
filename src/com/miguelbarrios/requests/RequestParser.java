package com.miguelbarrios.requests;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.miguelbarrios.gson.HistPollRanking;

public class RequestParser {
		
	private Gson deserializer;
	
	public RequestParser() {
		deserializer = new Gson();
	}
	
	public HistPollRanking parseHistoricalPoll(String json) {
		HistPollRanking[] rankings = deserializer.fromJson(json, HistPollRanking[].class);
		if(rankings.length == 0)
			return null;
		return rankings[0];
	}
	
}

//	public void rankingParser(String response, boolean regularSeason, Data dataHandler) {
//		//System.out.println(response);
//		Map<String, List<Poll>> polls = new HashMap<>();
//		
//		JSONArray arr = new JSONArray(response);
//		JSONObject obj = arr.getJSONObject(0);
//		JSONArray items = (JSONArray)obj.get("polls");
//		
//		int week = (regularSeason) ? obj.getInt("week") : -1;
//		int season = obj.getInt("season");
//		
//		for(int i = 0; i < items.length(); ++i) {
//			JSONObject element = (JSONObject) items.get(i);
//			JSONArray elements = (JSONArray) element.get("ranks");
//			String pollName = element.get("poll").toString();			
//			
//			if(pollName.equals("AFCA Division II Coaches Poll") || pollName.equals("FCS Coaches Poll"))
//				continue;
//
//			Poll currentPoll = new Poll(pollName, week, season);
//			for(int j = 0; j < elements.length(); ++j) {
//				JSONObject pollObj = elements.getJSONObject(j);
//				String cur = pollObj.toString();
//				int rank = pollObj.getInt("rank");
//				String school = pollObj.getString("school");
//				String conference = pollObj.get("conference").toString();
//				int points = pollObj.getInt("points");
//				PollItem item = new PollItem(school, rank, conference, points);
//				currentPoll.addItem(item);
//			}
//			
//			dataHandler.addPoll(currentPoll);
//
//		}
//	}