package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;

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
	
	public static void setOtrhoCam(OrthographicCamera cam){
		cam.setToOrtho(false, 1200, 800);
	}
	
	public static int getAsteroid() {
		return generateRandomInt(0,3);
	}
	
}
