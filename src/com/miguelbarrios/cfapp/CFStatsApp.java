package com.miguelbarrios.cfapp;

import com.miguelbarrios.cfpentities.StatGenerator;
import com.miguelbarrios.data_collection.drivers.DataLoader;
import com.miguelbarrios.data_collection.drivers.DataLoaderFile;


public class CFStatsApp {
	public static void main(String[] args) {
		
		DataLoader loader = new DataLoaderFile();
		loader.loadData();
		
		StatGenerator stats = new StatGenerator(loader.getData());
		
		//stats.AP25MostPointsInRange(2001, 2021);
		//stats.APConsistRankedInRange(1980,2021);
		//stats.numberOfAppearanceInPoll("AP Top 25", 2010, 2021, true);
	
		// TODO: most top N finishis in poll
		
	}
	
}
