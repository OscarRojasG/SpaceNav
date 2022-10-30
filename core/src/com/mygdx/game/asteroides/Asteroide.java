package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Nave;

public class Asteroide extends Enemigo {
	
    public Asteroide(float x, float y, int ancho, int alto, float velX, float velY, int puntaje, Texture tx) {
    	super(x, y, ancho, alto, velX, velY, puntaje, tx);
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

	@Override
	public void agregarEfecto(Nave nave) {
		nave.herir();
	}
	
}
