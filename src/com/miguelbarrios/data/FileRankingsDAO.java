package com.miguelbarrios.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.miguelbarrios.api_data_collection.gson_entities.HistoricalPolls;
import com.miguelbarrios.enteties.SeasonPoll;
import com.miguelbarrios.util.Utilities;

public class FileRankingsDAO implements RankingsDAO{
	

	@Override
	public List<SeasonPoll> getRankings() {
		
		List<SeasonPoll> res = new ArrayList<>();
		String p = "/Users/miguelbarrios/SD/Java/workspace/CFBStatsApp/src/Data/HistoricalPolls";
		List<String> files = Utilities.getFilesInDir(p);
		
		for (String fileName : files) {
			String filePath = "/Users/miguelbarrios/SD/Java/workspace/CFBStatsApp/src/Data/HistoricalPolls/" + fileName;
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					line = "[" + line + "]";
					HistoricalPolls poll = parser.parseHistoricalPollJson(line);
					List<SeasonPoll> seasonPolls = SeasonPoll.convertHistPollsToSeasonPoll(poll);
					res.addAll(seasonPolls);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
}
