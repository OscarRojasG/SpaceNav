package com.mygdx.game.enemigos.basuraEspacial;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;

public class BasuraEspacial extends Enemigo {
	
    public BasuraEspacial(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        this.getCuerpo().setAngularVelocity(0.0f);
    }

    @Override
	public void agregarEfecto(Nave nave) {
		nave.desacelerar();
	}
	
}
