package com.miguelbarrios.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miguelbarrios.data.FileRankingsDAO;
import com.miguelbarrios.data.RankingsDAO;
import com.miguelbarrios.enteties.SeasonPoll;

public class RankingServlet extends HttpServlet{
	
	private RankingsDAO rankingsDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		rankingsDAO = new FileRankingsDAO();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<SeasonPoll> polls = rankingsDAO.getRankings();
		
		PrintWriter pw = resp.getWriter();
		if(polls == null) {
			pw.println("Error reading data");
		}
		else {
		
			for(SeasonPoll poll : polls) {
				pw.println(poll.getYear() + " " + poll.getPollName() + " " + poll.getSeasonType() + " "+ poll.getWeek());
			}
			
			pw.close();
		}
		

	}

}
