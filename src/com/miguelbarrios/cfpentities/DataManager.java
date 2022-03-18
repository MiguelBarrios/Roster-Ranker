package com.miguelbarrios.cfpentities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.miguelbarrios.requests.*;
import com.miguelbarrios.gson.HistPollRanking;
import com.miguelbarrios.util.Utilities;

public class DataManager {

	public static void main(String[] args) {
		DataManager dataManager = new DataManager();
		
		//dataManager.loadDataFromFiles();
		
		int[] years = {2012,2011,2010};
		dataManager.retrieveAndSaveData(years);
	}

	private RequestBuilder requestBuilder;

	private RequestHandler requestHandler;

	private RequestParser requestParser;

	private Data data;

	public DataManager() {
		this.requestBuilder = new RequestBuilder();
		this.requestHandler = new RequestHandler();
		this.requestParser = new RequestParser();
		this.data = new Data();
	}
	


	public void getCFPHistPollBySeason(int year) {
		
	}

	// --------------------------- Completed Methods ------------------------------
	
	public void retrieveAndSaveData(int[] years) {
		for(int year : years) {
			List<HistPollRanking> polls  = getHistoricalPollsForSeason(year);
			String fileName = "Polls" + year + ".txt";
			saveDataToFile(fileName, polls);
		}
	}

	public List<HistPollRanking> loadDataFromAPI() {
		List<HistPollRanking> polls = getHistoricalPollsForSeason(2018);
		addPolls(polls);
		return polls;
	}
	
	public void addPolls(List<HistPollRanking> polls) {
		data.addPolls(polls);
	}

	public List<HistPollRanking> getHistoricalPollsForSeason(int year) {
		System.out.print("Getting polls.");
		List<HistPollRanking> polls = new ArrayList<>();
		// Get polls for each week of the reqular season
		for (int week = 1; week <= 15; ++week) {
			String requestAPPoll = requestBuilder.regularSeasonPollRequest(year, week);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			HistPollRanking ranking = requestParser.parseHistoricalPoll(response);
			polls.add(ranking);
			System.out.print(".");
		}
		System.out.println();

		String postSeasonPollRequest = requestBuilder.postSeasonPollRequest(year);
		String response = requestHandler.sendGetRequest(postSeasonPollRequest);
		HistPollRanking finalRanking = requestParser.parseHistoricalPoll(response);
		polls.add(finalRanking);
		return polls;
	}
	
	public void loadDataFromFiles() {
		List<HistPollRanking> polls = new ArrayList<>();
		List<String> files = Utilities.getFilesInDir("src/Data/HistoricalPolls/");
		for(String fileName : files) {
			System.out.println("Reading file: " + fileName);
			String filePath = "src/Data/HistoricalPolls/" + fileName;
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
				String line;
				while((line = br.readLine()) != null) {
					line = "[" + line + "]";
					HistPollRanking poll = requestParser.parseHistoricalPoll(line);
					polls.add(poll);
					
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		this.data.addPolls(polls);
	}
	
	public void saveDataToFile(String fileName, List<HistPollRanking> polls) {
		Gson gson = new Gson();

		try {
			FileWriter fw = new FileWriter("src/Data/HistoricalPolls/" + fileName);
			PrintWriter pw = new PrintWriter(fw);
			for (HistPollRanking poll : polls) {
				String json = gson.toJson(poll);
				pw.println(json);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
