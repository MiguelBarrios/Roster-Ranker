package com.miguelbarrios.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poll {

	private String name;
	
	private int week;
	
	private int season;

	private List<PollItem> items;
	
	public Poll(String name, int week, int season) {
		this.name = name;
		this.week = week;
		this.season = season;
		items = new ArrayList<>();
	}

	public void addItem(PollItem item) {
		this.items.add(item);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String pollInfo() {
		return name + " " + "week: " + week;
	}
	
	public void displayPoll() {
		System.out.println(pollInfo());
		for(PollItem item : items) {
			String res = String.format("\t%s coference: %s rank: %d points: %d", item.getSchool(), item.getConference(),item.getRank(), item.getPoint());
			System.out.println(res);
		}
	}
	
	


	
	

}
