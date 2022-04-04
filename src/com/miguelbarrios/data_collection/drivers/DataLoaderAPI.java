package com.miguelbarrios.data_collection.drivers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.data_collection.requests.RequestBuilder;
import com.miguelbarrios.data_collection.requests.RequestHandler;

public class DataLoaderAPI extends DataLoader {

	private RequestBuilder requestBuilder;

	private RequestHandler requestHandler;

	public DataLoaderAPI() {
		this.requestBuilder = new RequestBuilder();
		this.requestHandler = new RequestHandler();
	}

	public void retrieveAndSaveData(int year) {
		List<HistoricalPolls> polls = getHistoricalPollsForSeason(year);
		String fileName = "Polls" + year + ".txt";
		saveDataToFile(fileName, polls);
	}

	@Override
	public void loadData() {
		List<HistoricalPolls> polls = getHistoricalPollsForSeason(2012);
		addPolls(polls);
	}

	public List<HistoricalPolls> getHistoricalPollsForSeason(int year) {
		System.out.print("Getting polls for " + year + " .");
		List<HistoricalPolls> polls = new ArrayList<>();
		// Get polls for each week of the reqular season
		for (int week = 1; week <= 15; ++week) {
			String requestAPPoll = requestBuilder.regularSeasonPollRequest(year, week);
			String response = requestHandler.sendGetRequest(requestAPPoll);
			HistoricalPolls ranking = parser.parseHistoricalPollJson(response);
			polls.add(ranking);
			System.out.print(".");
		}
		System.out.println();

		String postSeasonPollRequest = requestBuilder.postSeasonPollRequest(year);
		String response = requestHandler.sendGetRequest(postSeasonPollRequest);
		HistoricalPolls finalRanking = parser.parseHistoricalPollJson(response);
		polls.add(finalRanking);
		return polls;
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
