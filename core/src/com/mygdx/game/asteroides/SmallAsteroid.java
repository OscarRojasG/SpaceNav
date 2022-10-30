package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util;

public class SmallAsteroid extends Asteroide{
	private static final int ancho = 40;
	private static final int alto = 40;
	private static final int puntaje = 5;
	private static final Texture image = new Texture(Gdx.files.internal("aGreySmall.png"));
	
    public SmallAsteroid(float velX, float velY) {
    	super(Util.generateRandomInt(ancho, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomInt(alto, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX, velY, puntaje, image);
    }
    
}