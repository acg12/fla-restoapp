package singleton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import models.Resto;
import models.ScoreComparator;
import models.ScoreData;

public class ReadWriteHighscore {
	
	private static ReadWriteHighscore _instance_ = null;
	private static File file;
	private static ArrayList<ScoreData> scores;

	private ReadWriteHighscore(ArrayList<ScoreData> scores, File file) {
		// TODO Auto-generated constructor stub
		this.scores = scores;
		this.file = file;
	}

	public static ReadWriteHighscore getInstance() {
		try {
			if(_instance_ == null) {
				synchronized(ReadWriteHighscore.class) {
					if(_instance_ == null) {
						_instance_ = new ReadWriteHighscore(new ArrayList<>(), new File("highscore.txt"));
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return _instance_;
	}
	
	public void saveScore() {
		// append to file
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			
			for(ScoreData sd : scores) {
				writer.write(sd.getName() + "#" + sd.getScore() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void readScores() {
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] array = data.trim().split("#");
				scores.add(new ScoreData(array[0], Integer.parseInt(array[1])));
			}
			
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(scores.size() > 0) {
			Collections.sort(scores, new ScoreComparator());
		}
	}
	
	public void saveTempData(Resto resto) {
		if(resto != null) {
			// udah main
			scores.add(new ScoreData(resto.getName(), resto.getScore()));
			Collections.sort(scores, new ScoreComparator());
		}
	}
	
	public void printScores(Resto resto) {
		// print highscores
		if(scores.size() == 0) {
			System.out.println("No data yet!");
		} else {
			System.out.println("HIGHSCORE");
			System.out.println("=================================================");
			System.out.println("     | Rank | Restaurant's Name | Score    |");
			System.out.println("=================================================");
			for(int i = 0; i < scores.size() && i < 10; i++) {
				ScoreData sd = scores.get(i);
				if(resto != null && resto.getName().equals(sd.getName())) {
					System.out.printf(" >>> | %-4d | %-17s | %-8d | <<<\n", (i + 1), sd.getName(), sd.getScore());
				} else {
					System.out.printf("     | %-4d | %-17s | %-8d |\n", (i + 1), sd.getName(), sd.getScore());
				}
			}
			System.out.println("=================================================");
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Press Enter to continue...");
		scan.nextLine();
	}
}
