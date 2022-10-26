package com.mygdx.game;

public interface Hiriente {
	public static final int moreScore = 10;
	public static final int lessScore = -10;
	
	public void agregarEfecto(Nave nave);
	
	public boolean enPantalla();
	
	public int getScoreChange();
}
