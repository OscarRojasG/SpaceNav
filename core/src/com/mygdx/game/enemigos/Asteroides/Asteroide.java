package com.mygdx.game.enemigos.Asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Asteroide extends Enemigo {
	
    public Asteroide(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        float r = Util.generateRandomFloat(0, 1);
        this.getCuerpo().setAngularVelocity(r);
        System.out.println("R:" + r);
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
