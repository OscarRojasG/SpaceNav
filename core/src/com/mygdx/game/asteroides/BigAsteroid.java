package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;

public class BigAsteroid extends Asteroide{
	private static final int ancho = 120;
	private static final int alto = 120;
	private static final int puntaje = 20;
	private static final Texture image = new Texture(Gdx.files.internal("aGreyLarge.png"));
	
    public BigAsteroid(float velX, float velY) {
    	super(Util.generateRandomFloat(0f, Gdx.graphics.getWidth()/(ancho*b2Modelo.getScale()) - ancho),
    		  Util.generateRandomFloat(0, Gdx.graphics.getHeight()/b2Modelo.getScale() - alto), 
    		  ancho, alto, velX, velY, puntaje, image);
    }
    
}