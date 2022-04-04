package com.miguelbarrios.data_collection.drivers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.util.Utilities;

public class DataLoaderFile extends DataLoader{

	@Override
	public void loadData() {
		List<String> files = Utilities.getFilesInDir("src/Data/HistoricalPolls/");
		List<HistoricalPolls> polls = null;
		for (String fileName : files) {
			String filePath = "src/Data/HistoricalPolls/" + fileName;
			polls = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					line = "[" + line + "]";
					HistoricalPolls poll = parser.parseHistoricalPollJson(line);
					polls.add(poll);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			data.addPolls(polls);
		}
	}
}
