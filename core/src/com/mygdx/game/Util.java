package com.mygdx.game;

import java.util.Random;

public class Util {
	private static final Random r = new Random();
	
	/**
	 * @param int min: Número mínimo a seleccionar.
	 * @param int max: Número máximo a seleccionar.
	 * @return int : Número aleatorio seleccionado entre el mínimo y máximo incluyendo dichos números.
	 * */
	public static int generateRandomInt(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}
	
	/**
	 * @return boolean: true o false de forma aleatoria.
	 * */
	public static int generateRandomSign() {
	    if(r.nextBoolean())
	        return -1;
	    else
	        return 1;
	}
	
	/**
	 * /**
	 * @param int a: Número posible de seleccionar.
	 * @param int b: Número posible de seleccionar.
	 * @return int : Número aleatorio seleccionado siendo a o b.
	 * */
	public static int generateRandomBetween(int a, int b) {
		int option = generateRandomInt(1,2);
		if(option == 1) 
		{
			return a;
		}
		return b;
	}
}
