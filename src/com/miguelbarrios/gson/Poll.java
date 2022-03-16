package com.miguelbarrios.gson;

public class Poll {
	private String poll;
	
	private Rank[] ranks;
	
	public class Rank{
		private int rank;
		
		private String school;
		
		private String conference;
		
		private int firstPlaceVotes;
		
		private int points;

		public Rank(int rank, String school, String conference, int firstPlaceVotes, int points) {
			super();
			this.rank = rank;
			this.school = school;
			this.conference = conference;
			this.firstPlaceVotes = firstPlaceVotes;
			this.points = points;
		}
		
		
	}
	
	public Poll(String poll) {
		this.poll = poll;
	}
}
