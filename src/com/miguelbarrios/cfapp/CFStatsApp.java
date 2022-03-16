package com.miguelbarrios.cfapp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.miguelbarrios.cfpentities.ApPointsComparator;
import com.miguelbarrios.cfpentities.Data;
import com.miguelbarrios.cfpentities.StatGenerator;

public class CFStatsApp {
	public static void main(String[] args) {
		CFStatsApp app = new CFStatsApp();
		app.run();
	}
	
	public void run() {
		Gson gson = new Gson();
		
		Data data = new Data();
		data.loadData(2021, 2021);
		data.displayPolls();
		
		
		
//		Data data = new Data();
//		int startYear = 2017;
//		int stopYear = 2021;
//		
//		data.loadData(startYear, stopYear);
//		//data.displayPolls();
//		
//		StatGenerator stats = new StatGenerator(data);
//		inRangeAllTimeFinalWinner("AP Top 25", startYear, stopYear, stats);
	}
	
	public void inRangeAllTimeFinalWinner(String pollName, int startYear, int stopYear, StatGenerator stats) {
		Map<String, Integer> map = stats.inRangeAllTimeFinalWinner(pollName, startYear, stopYear);
		
		// Sort map by total number of points
		List<String> list = new ArrayList<>(map.keySet());
		Comparator<String> comparator = new ApPointsComparator(map);
		list.sort(comparator);
		
		System.out.println("Ap Top 25: " + startYear + "-" + stopYear + " Total AP points");
		for(String key : list) {
			System.out.println(key + " " + map.get(key));
		}
	}
	
}
