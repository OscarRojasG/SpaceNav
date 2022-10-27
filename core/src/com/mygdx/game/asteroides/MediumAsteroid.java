package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util;
import com.mygdx.game.DamageNave;

public class MediumAsteroid extends DamageNave{
	private final static int ancho = 80;
	private final static int alto = 80;
	private final static Texture image = new Texture(Gdx.files.internal("aGreyMedium4.png"));
	
    public MediumAsteroid(float velX, float velY) {
    	super(Util.generateRandomInt(ancho, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomInt(alto, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX, velY, image);
    }
    
    @Override
    public void setPosition(float x, float y) {
    	if (x + getAncho() > Gdx.graphics.getWidth()) {
        	x = Gdx.graphics.getWidth() - getAncho();
        	setVelocidadX(getVelocidadX() * -1);
        }
        else if (x < 0) {
        	x = 0;
        	setVelocidadX(getVelocidadX() * -1);
        }
        
        if (y + getAlto() > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - getAlto();
        	setVelocidadY(getVelocidadY() * -1);
        }
        else if (y < 0) {
        	y = 0;
        	setVelocidadY(getVelocidadY() * -1);
        }
        
        super.setPosition(x,y);
    }
}
