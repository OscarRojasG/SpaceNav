package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public interface Hiriente{
	public static final int moreScore = 20;
	public static final int lessScore = -20;
	
	public abstract void agregarEfecto(Nave nave);
	
	public boolean enPantalla();
	
	public int getScoreChange();
}
