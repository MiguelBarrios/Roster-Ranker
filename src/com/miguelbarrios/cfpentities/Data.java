package com.miguelbarrios.cfpentities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miguelbarrios.requests.RequestBuilder;
import com.miguelbarrios.requests.RequestHandler;
import com.miguelbarrios.requests.RequestParser;

public class Data {
	
	private List<HistoricalPolls> historicalPolls;

	public Data() {
		historicalPolls = new ArrayList<>();
	}
	
	public void addPolls(List<HistoricalPolls> polls) {
		historicalPolls.addAll(polls);
	}
	
	public List<HistoricalPolls> getHistPolls() {
		List<HistoricalPolls> res = new ArrayList<>(historicalPolls);
		return res;
	}
	
}
