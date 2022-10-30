package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util;

public class MediumAsteroid extends Asteroide{
	private static final int ancho = 80;
	private static final int alto = 80;
	private static final int puntaje = 10;
	private static final Texture image = new Texture(Gdx.files.internal("aGreyMedium4.png"));
	
    public MediumAsteroid(float velX, float velY) {
    	super(Util.generateRandomInt(ancho, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomInt(alto, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX, velY, puntaje, image);
    }
    
}
