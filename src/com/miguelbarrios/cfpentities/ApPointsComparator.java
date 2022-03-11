package com.miguelbarrios.cfpentities;

import java.util.Comparator;
import java.util.Map;

public class ApPointsComparator implements Comparator<String>{

	private Map<String, Integer> map;
	
	public ApPointsComparator(Map<String, Integer> map) {
		this.map = map;
	}
	
	@Override
	public int compare(String o1, String o2) {
		
		int pointsA = map.get(o1);
		int pointsB = map.get(o2);
		
		int res = 0;
		if(pointsA < pointsB) {
			res = 1;
		}
		else if(pointsA > pointsB) {
			res = -1;
		}
		
		return res;
	}

}
