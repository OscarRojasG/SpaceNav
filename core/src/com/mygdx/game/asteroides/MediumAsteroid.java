package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;

public class MediumAsteroid extends Asteroide{
	private static final float ancho = 40 / b2Modelo.getScale();
	private static final float alto = 40  / b2Modelo.getScale();
	private static final int puntaje = 10;
	
    public MediumAsteroid(float velX, float velY) {
    	super(Util.generateRandomFloat(ancho, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomFloat(alto, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX, velY, puntaje);
    }
    
}
