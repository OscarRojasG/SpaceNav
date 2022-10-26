package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public interface Hiriente{
	
	public static final Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
	
	public boolean enPantalla();
	
	public abstract void agregarEfecto(Nave nave);
	
	public int getScoreChange();
}
