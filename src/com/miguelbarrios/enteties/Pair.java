package com.miguelbarrios.enteties;

public class Pair {
	public String school;
	
	public int points;
	
	public Pair(String school, int points) {
		this.school = school;
		this.points = points;
	}
	
	public String toString() {
		return school + " " + points;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
