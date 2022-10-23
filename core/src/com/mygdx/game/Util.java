package com.mygdx.game;

import java.util.Random;

public class Util {
	private static final Random r = new Random();
	
	public static int generateRandomInt(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}
	
	public static int generateRandomSign() {
	    if(r.nextBoolean())
	        return -1;
	    else
	        return 1;
	}
}