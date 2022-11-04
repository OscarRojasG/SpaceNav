package com.mygdx.game.enemigos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Nave;
import com.mygdx.game.b2Modelo;

public class DesechoCohete extends Enemigo {
	private static final float ancho = 40/b2Modelo.getScale();
	private static final float alto = 60/b2Modelo.getScale();
	private static final int puntaje = 20;
	
	public DesechoCohete(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, puntaje);
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
