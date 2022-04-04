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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public int getFirstPlaceVotes() {
		return firstPlaceVotes;
	}

	public void setFirstPlaceVotes(int firstPlaceVotes) {
		this.firstPlaceVotes = firstPlaceVotes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
}
