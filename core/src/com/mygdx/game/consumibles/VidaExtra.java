package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Consumible;
import com.mygdx.game.Nave4;
import com.mygdx.game.ObjetoEspacial;

public class VidaExtra extends ObjetoEspacial implements Consumible {

	public VidaExtra(float x, float y, float velX, float velY, float width, float height, Texture tx) {
		super(x, y, velX, velY, width, height, tx);
	}

	@Override
	public void agregarEfecto(Nave4 nave) {
		nave.agregarVida();
	}

	@Override
	public void enColision(Nave4 nave) {
		agregarEfecto(nave);
	}

	@Override
	public void update() {
		float x = getX() + getVelocityX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocityY() * Gdx.graphics.getDeltaTime();
        setPosition(x,y);
	}
	
}
