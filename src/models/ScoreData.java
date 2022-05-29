package models;

public class ScoreData {

	private String name;
	private int score;
	
	public ScoreData(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
}
