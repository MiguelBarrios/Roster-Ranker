package com.miguelbarrios.api_data_collection.gson_entities;

import java.util.Arrays;

public class Poll {
	private String poll;
	
	private Rank[] ranks;
	
	public Poll(String poll) {
		this.poll = poll;
	}

	public String getPoll() {
		return poll;
	}

	public void setPoll(String poll) {
		this.poll = poll;
	}

	public Rank[] getRanks() {
		return ranks;
	}

	public void setRanks(Rank[] ranks) {
		this.ranks = ranks;
	}
	
	public String toString() {
		return poll + " : " + Arrays.toString(ranks);
		
	}
	
	
	
}
