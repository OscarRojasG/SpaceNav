package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class Bala extends ObjetoMovil {
	    
    public Bala(float x, float y, float ancho, float alto, float vel, Texture tx) {
    	super(x, y, ancho, alto, 0, vel, tx);
    }
    
	@Override
	public void actualizar() {
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setY(y);
	}
	
}
