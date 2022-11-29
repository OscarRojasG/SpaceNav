package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Nave;

public class Supernave extends Consumible {
	private final static float ancho =  35f/26;
	private final static float alto = 42.24f/26;
	private final static float tiempoMaximo = 6;
	private final static Texture image = new Texture(Gdx.files.internal("supernave.png"));
	
	public Supernave(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, tiempoMaximo, image);
		setVelocidadX(velX);
		setVelocidadY(velY);
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.mejorar(5);
	}
	
}