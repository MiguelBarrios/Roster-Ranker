package com.miguelbarrios.requests;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestParser {
	public void teamGameStatsRequestParser(String response) {
		System.out.println(response);
		JSONArray arr = new JSONArray(response);
		for(int i = 0; i < arr.length(); ++i) {
			System.out.println(arr.get(i));
		}

	}
	
	
	public void rankingParser(String response) {
		
		JSONArray arr = new JSONArray(response);
		JSONObject obj = arr.getJSONObject(0);
		JSONArray items = (JSONArray)obj.get("polls");
		int week = obj.getInt("week");
		
		for(int i = 0; i < items.length(); ++i) {
			JSONObject element = (JSONObject) items.get(i);
			JSONArray elements = (JSONArray) element.get("ranks");
			String pollName = element.get("poll").toString();			
			
			if(pollName.equals("AFCA Division II Coaches Poll") || pollName.equals("FCS Coaches Poll"))
				continue;

			Poll currentPoll = new Poll(pollName, week);
			for(int j = 0; j < elements.length(); ++j) {
				JSONObject pollObj = elements.getJSONObject(j);
				String cur = pollObj.toString();
				int rank = pollObj.getInt("rank");
				String school = pollObj.getString("school");
				String conference = pollObj.get("conference").toString();
				int points = pollObj.getInt("points");
				PollItem item = new PollItem(school, rank, conference, points);
				currentPoll.addItem(item);
			}
			Data.addPoll(currentPoll);
		}		
	}
}
