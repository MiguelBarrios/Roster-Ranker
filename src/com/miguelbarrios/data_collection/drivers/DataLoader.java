package com.miguelbarrios.data_collection.drivers;

import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.cfpentities.Data;
import com.miguelbarrios.data_collection.requests.Parser;

public abstract class DataLoader {
	
	protected Data data;
	
	protected Parser parser;
	
	public abstract void loadData();
	
	public DataLoader() {
		data = new Data();
		parser = new Parser();
	}
	
	public List<HistoricalPolls> getHistoricalPolls() {
		return data.getHistPolls();
	}
	
	public void addPolls(List<HistoricalPolls> polls) {
		data.addPolls(polls);
	}
	
	public Data getData() {
		return data;
	}
}
