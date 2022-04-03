package com.miguelbarrios.data_collection.drivers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import com.google.gson.Gson;
import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.api_data_collection.gson_entities.Poll;
import com.miguelbarrios.data_collection.requests.RequestBuilder;
import com.miguelbarrios.data_collection.requests.RequestHandler;
import com.miguelbarrios.util.Utilities;

public class DataLoaderAPI extends DataLoader{

	private RequestBuilder requestBuilder;

	private RequestHandler requestHandler;

	public DataLoaderAPI() {
		this.requestBuilder = new RequestBuilder();
		this.requestHandler = new RequestHandler();
	}
	
	public List<HistoricalPolls> getPollsInRange(int startYear, int stopYear){
		
		List<HistoricalPolls> filtered = new ArrayList<>();
		for(HistoricalPolls p : getHistoricalPolls()) {
			int year = p.getSeason();
			if(year >= startYear && year <= stopYear) {
				filtered.add(p);
			}
		}
		return filtered;
	}

	public List<HistoricalPolls> filterHistoricalPolls(String str, BiPredicate<HistoricalPolls, String> matcher) {
		List<HistoricalPolls> filtered = new ArrayList<>();
		for (HistoricalPolls p : getHistoricalPolls()) {
			if (matcher.test(p, str)) {
				filtered.add(p);
			}
		}
		return filtered;
	}

	public List<Poll> filterPolls(String string, BiPredicate<Poll, String> matcher) {
		List<Poll> filtered = new ArrayList<>();
		for (HistoricalPolls p : getHistoricalPolls()) {
			for (Poll poll : p.getPolls()) {
				if (matcher.test(poll, string)) {
					filtered.add(poll);
				}
			}
		}

		return filtered;
	}

	// --------------------------- Completed Methods ------------------------------


	public void retrieveAndSaveData(int[] years) {
		for (int year : years) {
			List<HistoricalPolls> polls = getHistoricalPollsForSeason(year);
			String fileName = "Polls" + year + ".txt";
			saveDataToFile(fileName, polls);
		}
	}

	public List<HistoricalPolls> loadDataFromAPI() {
		List<HistoricalPolls> polls = getHistoricalPollsForSeason(2018);
		addPolls(polls);
		return polls;
	}

	public List<HistoricalPolls> getHistoricalPollsForSeason(int year) {
		System.out.print("Getting polls.");
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

	public void loadDataFromFiles() {
		List<String> files = Utilities.getFilesInDir("src/Data/HistoricalPolls/");
		for (String fileName : files) {
			String filePath = "src/Data/HistoricalPolls/" + fileName;
			List<HistoricalPolls> polls = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					line = "[" + line + "]";
					HistoricalPolls poll = parser.parseHistoricalPollJson(line);
					//System.out.println(poll);
					polls.add(poll);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			data.addPolls(polls);
		}
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
