package com.miguelbarrios.api_data_collection.gson_entities;

public class Rank {
	private int rank;
	
	private String school;
	
	private String conference;
	
	private int firstPlaceVotes;
	
	private int points;

	public Rank(int rank, String school, String conference, int firstPlaceVotes, int points) {
		super();
		this.rank = rank;
		this.school = school;
		this.conference = conference;
		this.firstPlaceVotes = firstPlaceVotes;
		this.points = points;
	}

	@Override
	public String toString() {
		return "[" + school + "(" + rank + ") " + conference + ", firstPlaceVotes="
				+ firstPlaceVotes + ", points=" + points + "]";
	}
	
	
}
