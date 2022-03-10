package com.miguelbarrios.cfapp;
import com.miguelbarrios.cfpentities.Data;
import com.miguelbarrios.cfpentities.StatGenerator;

public class CFStatsApp {
	public static void main(String[] args) {
		CFStatsApp app = new CFStatsApp();
		app.run();
	}
	
	public void run() {
		Data data = new Data();
		data.loadData(2019, 2021);
		//data.displayPolls();
		
		StatGenerator stats = new StatGenerator(data);
		stats.inRangeAllTimeWinner("AP Top 25", 2019, 2021);
		
	}
	
}
