package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Nave;

public class VidaExtra extends Consumible {
	private final static float ancho = 0.7f;
	private final static float alto = 0.7f;
	private final static float tiempoMaximo = 3;
	private final static Texture image = new Texture(Gdx.files.internal("health.png"));
	
	public VidaExtra(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, tiempoMaximo, image);
		setVelocidadX(velX);
		setVelocidadY(velY);
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.agregarVida();
	}
	
}
