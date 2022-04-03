package com.miguelbarrios.cfapp;

import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.data_collection.drivers.DataLoaderAPI;
import com.miguelbarrios.data_collection.requests.RequestBuilder;
import com.miguelbarrios.data_collection.requests.RequestHandler;
import com.miguelbarrios.data_collection.requests.Parser;

public class DataCollectionApp {
	
	public static void main(String[] args) {
		DataLoaderAPI dataManager = new DataLoaderAPI();
		int[] years = {1999,1998};
		dataManager.retrieveAndSaveData(years);
	}
	
	private RequestBuilder requestBuilder;

	private RequestHandler requestHandler;

	private Parser requestParser;
	
	private List<HistoricalPolls> historicalPolls;
	
	
	
	
}
