package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


public abstract class Asteroide extends ObjetoEspacial {

    public Asteroide(float x, float y, int ancho, int alto, float velX, float velY, Texture tx) {
    	super(x, y, ancho, alto, velX, velY, tx);
    }
    
    public abstract boolean verificarColision(Asteroide a2);
    
    public abstract boolean verificarColision(Bala bala);
}
