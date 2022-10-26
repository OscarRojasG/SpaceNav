package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public interface Hiriente{
	public static final int moreScore = 10;
	public static final int lessScore = -10;
	public static final Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
	
	public boolean enPantalla();
	
	public int getScoreChange();
}
