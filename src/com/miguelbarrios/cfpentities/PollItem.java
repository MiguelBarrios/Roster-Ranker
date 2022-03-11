package com.miguelbarrios.cfpentities;

public class PollItem {
	
	private String school;
	
	private int rank;
	
	private String conference;
	
	private int points;

	private PollItem() {
		
	}
	
	public PollItem(String school, int rank, String conference, int point) {
		this.school = school;
		this.rank = rank;
		this.points = point;
		if(conference == null)
			this.conference = "";
		else
			this.conference = conference;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public int getPoints() {
		return points;
	}

	public void setPoint(int point) {
		this.points = point;
	}

	@Override
	public String toString() {
		return "PollItem [school=" + school + ", rank=" + rank + ", conference=" + conference + ", point=" + points
				+ "]";
	}
	
	
	
	

}
