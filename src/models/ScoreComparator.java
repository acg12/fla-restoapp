package models;

import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreData> {

	@Override
	public int compare(ScoreData o1, ScoreData o2) {
		// TODO Auto-generated method stub
		return o2.getScore() - o1.getScore();
	}

}
