package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Consumible;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;

public class VidaExtra extends ObjetoEspacial implements Consumible {

	public VidaExtra(float x, float y, float width, float height, float velX, float velY, Texture tx) {
		super(x, y, width, height, velX, velY, tx);
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.agregarVida();
	}

	@Override
	public void actualizar() {
		float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setPosition(x,y);
	}
	
}