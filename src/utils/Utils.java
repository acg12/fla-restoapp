package utils;

import java.util.Random;

import models.Resto;

public class Utils {

	private static Random rand = new Random();
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String generateName(Resto resto) {
		String random = "";
		do {
			random = "";
			for(int j = 0; j < 2; j++) {
				random += alphabet.charAt(rand.nextInt(alphabet.length()));
			}
		} while(!Validate.isUniqueName(resto, random));
		
		return random;
	}
	
}
