package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class Bala extends ObjetoMovil {
	    
    public Bala(float x, float y, float ancho, float alto, float velx, float vely, Texture tx) {
    	super(x, y, ancho, alto, velx, vely, tx);
    }
    
	@Override
	public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setX(x);
        setY(y);
	}
	
}
