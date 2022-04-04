package com.miguelbarrios.cfapp;

import com.miguelbarrios.cfpentities.StatGenerator;
import com.miguelbarrios.data_collection.drivers.DataLoader;
import com.miguelbarrios.data_collection.drivers.DataLoaderFile;
import com.miguelbarrios.enteties.SeasonPoll;

public class CFStatsApp {
	public static void main(String[] args) {
		
		DataLoader loader = new DataLoaderFile();
		loader.loadData();
		
		StatGenerator stats = new StatGenerator(loader.getData());
		
		stats.APMostPointsInRange(2014, 2021);
	
	}
}
