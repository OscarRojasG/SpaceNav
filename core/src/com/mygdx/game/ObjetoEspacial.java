package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class ObjetoEspacial extends ObjetoMovil {
    
    public ObjetoEspacial(float x, float y, float width, float height, float velX, float velY, Texture tx) {
    	super(x, y, width, height, velX, velY, tx);
    }
    
	public boolean verificarColision(Nave nave) {
		return getArea().overlaps(nave.getArea());
	}
	
}
