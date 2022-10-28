package com.mygdx.game.enemigos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;

public class Satelite extends Enemigo implements Hiriente{
	private final static int ancho = 60;
	private final static int alto = 40;
	private final static int scoreChange = -20; // El satelite aun estaba en uso
	private final static Texture image = new Texture(Gdx.files.internal("Satelite.png"));
	
	public Satelite(float x, float y, 
            			float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, image);
	}
	
	@Override
	public void agregarEfecto(Nave nave) {
		nave.desacelerar();
	}

	@Override
	public int getScoreChange() {
		if (Util.generateRandomInt(0, 1) == 0) {
			return scoreChange * -1; // Esto es cuando el satelite era basura espacial
		}
		return scoreChange; // Esto es cuando el satelite estaba funcional
	}
}
