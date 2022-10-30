package com.mygdx.game.enemigos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Nave;

public class DesechoCohete extends Enemigo {
	private static final float ancho = 40;
	private static final float alto = 60;
	private static final int puntaje = 20;
	private static final Texture image = new Texture(Gdx.files.internal("RocketEngine.png"));
	
	public DesechoCohete(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, puntaje, image);
	}
	
	@Override
	public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        
        setPosition(x,y);
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.desacelerar();
	}
	
}
