package com.mygdx.game.enemigos.basuraEspacial;

import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;

public class BasuraEspacial extends Enemigo {
	
    public BasuraEspacial(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        this.getCuerpo().setAngularVelocity(0.0f);
        this.setCollisionData(FiguraBits.BASURA_ESPACIAL.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit));
    }
	
}