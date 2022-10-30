package com.mygdx.game.enemigos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Nave;

public class Satelite extends Enemigo {
	private static final float ancho = 60;
	private static final float alto = 40;
	private static final int puntaje = 15;
	private static final Texture image = new Texture(Gdx.files.internal("Satelite.png"));
	
	public Satelite(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, puntaje, image);
	}
	
	@Override
	public void agregarEfecto(Nave nave) {
		nave.desacelerar();
	}

}
