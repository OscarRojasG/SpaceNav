package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Figura {
	private Sprite sprite;
	
	public void startSprite(Texture tx, float ancho, float alto, float x, float y) {
		// Devolverlo a constructor genero error no se que cambie asi que me devuelvo
		setTexture(tx);
		setSize(ancho, alto);
		setX(x);
		setY(y);
	}
	
	public void setTexture(Texture tx) {
		sprite = new Sprite(tx);
	}
	
    public void dibujar(SpriteBatch batch) {
    	sprite.draw(batch);
    }
	
    public void setPosition(float x, float y) {
    	setX(x);
    	setY(y);
    }
    
    public void setX(float x) {
    	sprite.setX(x);
    }
    
    public void setY(float y) {
    	sprite.setY(y);
    }
    
    public void setSize(float ancho, float alto) {
    	sprite.setSize(ancho, alto);
    }

    public void setRotation(float angle) {
        sprite.setRotation(angle);
    }
	
	public float getX() {
		return sprite.getX();
	}
	
	public float getY() {
		return sprite.getY();
	}
	
	public float getAncho() {
		return sprite.getWidth();
	}
	
	public float getAlto() {
		return sprite.getHeight();
	}
	
    public Rectangle getArea() {
    	return sprite.getBoundingRectangle();
    }
    
    public boolean isOffscreen() {
        if (sprite.getX() < 0 || sprite.getX() + sprite.getWidth() > Gdx.graphics.getWidth()) 
            return true;
        
        if (sprite.getY() < 0 || sprite.getY() + sprite.getHeight() > Gdx.graphics.getHeight())
        	return true;
        
        return false;
    }
    
}
