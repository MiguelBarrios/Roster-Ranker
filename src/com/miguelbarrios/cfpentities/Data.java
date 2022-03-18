package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miguelbarrios.gson.HistPollRanking;
import com.miguelbarrios.requests.RequestBuilder;
import com.miguelbarrios.requests.RequestHandler;
import com.miguelbarrios.requests.RequestParser;

public class Data {
	
	private List<HistPollRanking> historicalPolls;

	public Data() {
		historicalPolls = new ArrayList<>();
	}
	
	public void addPolls(List<HistPollRanking> polls) {
		historicalPolls.addAll(polls);
	}
	
	public List<HistPollRanking> getHistPolls() {
		List<HistPollRanking> res = new ArrayList<>(historicalPolls);
		return res;
	}
	
	
}
