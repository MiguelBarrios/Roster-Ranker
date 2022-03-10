package com.miguelbarrios.requests;

public class RequestBuilder {

	private String baseURL = "https://api.collegefootballdata.com/";

	public String buildPollRequest(int year, int week) {
	    String request = baseURL + String.format("rankings?year=%d&week=%d&seasonType=regular", year, week);
	    return request;
	}
}
