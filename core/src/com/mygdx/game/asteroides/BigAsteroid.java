package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util;

public class BigAsteroid extends Asteroide{
	private final static int ancho = 120;
	private final static int alto = 120;
	private final static Texture image = new Texture(Gdx.files.internal("aGreyLarge.png"));
	
    public BigAsteroid(float velX, float velY) {
    	super(Util.generateRandomInt(0, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomInt(0, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX - 5, velY - 5, image);
    }
}