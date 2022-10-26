package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public interface Hiriente{
	
	public boolean enPantalla();
	
	public abstract void agregarEfecto(Nave nave);
	
	public int getScoreChange();
}
