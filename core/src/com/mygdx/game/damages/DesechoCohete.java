package com.mygdx.game.damages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Consumible;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;

public class DesechoCohete extends ObjetoEspacial implements Hiriente{
	private final static int ancho = 40;
	private final static int alto = 60;
	private final static Texture image = new Texture(Gdx.files.internal("RocketEngine.png"));
	
	public DesechoCohete(float x, float y, 
			             float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, image);
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.quitarVida();
	}

	@Override
	public boolean enPantalla() {
		return false;
	}

	@Override
	public void actualizar() {
		
	}

	@Override
	public int getScoreChange() {
		return moreScore;
	}
}
