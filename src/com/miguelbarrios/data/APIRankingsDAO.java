package com.miguelbarrios.data;

import java.util.List;

import com.miguelbarrios.data_collection.requests.RequestBuilder;
import com.miguelbarrios.data_collection.requests.RequestHandler;
import com.miguelbarrios.enteties.SeasonPoll;

public class APIRankingsDAO implements RankingsDAO {
	
	private RequestBuilder requestBuilder;

	private RequestHandler requestHandler;
	
	public APIRankingsDAO() {
		this.requestBuilder = new RequestBuilder();
		this.requestHandler = new RequestHandler();
	}

	@Override
	public List<SeasonPoll> getRankings() {
		// TODO Auto-generated method stub
		return null;
	}

}
