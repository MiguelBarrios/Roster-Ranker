package com.miguelbarrios.data;

import java.util.List;

import com.miguelbarrios.data_collection.requests.Parser;
import com.miguelbarrios.enteties.SeasonPoll;

public interface RankingsDAO {
	Parser parser = new Parser();
	List<SeasonPoll> getRankings();
}
