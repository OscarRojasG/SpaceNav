package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Nave;

public class VidaExtra extends Consumible {
	private final static int ancho = 40;
	private final static int alto = 40;
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
