package com.mygdx.game.damages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DamageNave;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;

public class Satelite extends DamageNave implements Hiriente{
	private final static int ancho = 60;
	private final static int alto = 40;
	private final static int scoreChange = -20; // El satelite aun estaba en uso
	private final static Texture image = new Texture(Gdx.files.internal("Satelite.png"));
	
	public Satelite(float x, float y, 
            			float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, image);
	}

	@Override
	public boolean enPantalla() {
		//return !isOffscreen();
		return true;
	}
	
	@Override
	public void agregarEfecto(Nave nave) {
//		nave.desacelerar();
	}

	@Override
	public int getScoreChange() {
		return scoreChange;
	}
}
