package com.miguelbarrios.requests;

public class RequestBuilder {

	private String baseURL = "https://api.collegefootballdata.com/";

	public String regularSeasonPollRequest(int year, int week) {
	    String request = baseURL + String.format("rankings?year=%d&week=%d&seasonType=regular", year, week);
	    return request;
	}
	
	public String postSeasonPollRequest(int year) {
		return String.format("%srankings?year=%d&seasonType=postseason", baseURL, year);
	}
}
