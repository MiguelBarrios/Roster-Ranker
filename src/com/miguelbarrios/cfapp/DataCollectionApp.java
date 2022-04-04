package com.miguelbarrios.cfapp;

import com.miguelbarrios.data_collection.drivers.DataLoaderAPI;

public class DataCollectionApp {
	
	public static void main(String[] args) {
		//saveAPIHistPolls(1980,2021);
	}
	
	public static void saveAPIHistPolls(int startYear, int stopYear) {
		DataLoaderAPI dataLoader = new DataLoaderAPI();
		for(int i = startYear; i <= stopYear; ++i)
			dataLoader.retrieveAndSaveData(i);
	}
	
	
	
}
