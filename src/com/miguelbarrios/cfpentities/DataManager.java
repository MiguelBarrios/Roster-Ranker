package com.miguelbarrios.cfpentities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.miguelbarrios.interfaces.HistoricalPollMatcher;
import com.miguelbarrios.interfaces.PollMatcher;
import com.miguelbarrios.requests.*;
import com.miguelbarrios.util.Utilities;

public class DataManager {

	public static void main(String[] args) {
		DataManager dataManager = new DataManager();
		
		
		dataManager.loadDataFromFiles();
		
		// Filter Polls by year
		List<HistoricalPolls> historicalPolls = dataManager.filterHistoricalPolls("2021", (poll, year) -> {
			return poll.getSeason() == Integer.parseInt(year);
		});
		
		
		// Filter by pollName
		List<Poll> polls = dataManager.filterPolls("AP Top 25", (Poll p, String pollName) -> {
			return p.getPoll().equalsIgnoreCase(pollName);
		});
		
		for(Poll poll : polls) {
			System.out.println(poll);
		}
		
		
		System.out.println("End");
		
		
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

	public List<HistoricalPolls> filterHistoricalPolls(String string, HistoricalPollMatcher matcher) {
		List<HistoricalPolls> filtered = new ArrayList<>();
		for (HistoricalPolls p : getHistoricalPolls()) {
			if (matcher.matches(p, string)) {
				filtered.add(p);
			}
		}
		return filtered;
	}
	
	public List<Poll> filterPolls(String string, PollMatcher matcher) {
		List<Poll> filtered = new ArrayList<>();
		for (HistoricalPolls p : getHistoricalPolls()) {
			for(Poll poll : p.getPolls()) {
				if (matcher.matches(poll, string)) {
					filtered.add(poll);
				}
			}
		}
		
		return filtered;
	}
	
	
	
	// --------------------------- Completed Methods ------------------------------
	public List<HistoricalPolls> getHistoricalPolls(){
		
		return data.getHistPolls();
	}
	
	public void retrieveAndSaveData(int[] years) {
		for(int year : years) {
			List<HistoricalPolls> polls  = getHistoricalPollsForSeason(year);
			String fileName = "Polls" + year + ".txt";
			saveDataToFile(fileName, polls);
		}
	}

	public List<HistoricalPolls> loadDataFromAPI() {
		List<HistoricalPolls> polls = getHistoricalPollsForSeason(2018);
		addPolls(polls);
		return polls;
	}
	
	public void addPolls(List<HistoricalPolls> polls) {
		data.addPolls(polls);
	}

	public List<HistoricalPolls> getHistoricalPollsForSeason(int year) {
		System.out.print("Getting polls.");
		List<HistoricalPolls> polls = new ArrayList<>();
		// Get polls for each week of the reqular season
		for (int week = 1; week <= 15; ++week) {
			String requestAPPoll = requestBuilder.regularSeasonPollRequest(year, week);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			HistoricalPolls ranking = requestParser.parseHistoricalPoll(response);
			polls.add(ranking);
			System.out.print(".");
		}
		System.out.println();

		String postSeasonPollRequest = requestBuilder.postSeasonPollRequest(year);
		String response = requestHandler.sendGetRequest(postSeasonPollRequest);
		HistoricalPolls finalRanking = requestParser.parseHistoricalPoll(response);
		polls.add(finalRanking);
		return polls;
	}
	
	public void loadDataFromFiles() {
		List<HistoricalPolls> polls = new ArrayList<>();
		List<String> files = Utilities.getFilesInDir("src/Data/HistoricalPolls/");
		for(String fileName : files) {
			System.out.println("Reading file: " + fileName);
			String filePath = "src/Data/HistoricalPolls/" + fileName;
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
				String line;
				while((line = br.readLine()) != null) {
					line = "[" + line + "]";
					HistoricalPolls poll = requestParser.parseHistoricalPoll(line);
					polls.add(poll);
					
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		this.data.addPolls(polls);
	}
	
	public void saveDataToFile(String fileName, List<HistoricalPolls> polls) {
		Gson gson = new Gson();

		try {
			FileWriter fw = new FileWriter("src/Data/HistoricalPolls/" + fileName);
			PrintWriter pw = new PrintWriter(fw);
			for (HistoricalPolls poll : polls) {
				String json = gson.toJson(poll);
				pw.println(json);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
