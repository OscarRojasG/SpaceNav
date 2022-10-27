package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Asteroide;
import com.mygdx.game.Util;

public class SmallAsteroid extends Asteroide{
	private final static int ancho = 30;
	private final static int alto = 30;
	private final static Texture image = new Texture(Gdx.files.internal("aGreySmall.png"));
	
    public SmallAsteroid(float velX, float velY) {
    	super(Util.generateRandomInt(ancho, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomInt(alto, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX, velY, image);
    }
    
}