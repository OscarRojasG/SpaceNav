package com.mygdx.game.damages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;

public class Satelite extends ObjetoEspacial implements Hiriente{
	private final static int ancho = 60;
	private final static int alto = 40;
	private final static Texture image = new Texture(Gdx.files.internal("Satelite.png"));
	
	public Satelite(float x, float y, 
            			float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, image);
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.quitarVida();
	}

	@Override
	public void actualizar() {
		
	}

	@Override
	public boolean enPantalla() {
		return false;
	}

	@Override
	public int getScoreChange() {
		return lessScore;
	}
}
