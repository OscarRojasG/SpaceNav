package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoEspacial {
    private float velX;
    private float velY;
    private Sprite spr;
    
    public ObjetoEspacial(float x, float y, float velX, float velY, float width, float height, Texture tx) {
    	spr = new Sprite(tx);
        spr.setPosition(x, y);
        spr.setSize(width, height);
        
        this.setVelocityX(velX);
        this.setVelocityY(velY);
    }
    
    public abstract void enColision(Nave4 nave);
    
	public boolean existeColision(Nave4 nave) {
		return this.getArea().overlaps(nave.getArea());
	}
	
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
	
    public void setPosition(float x, float y) {
    	setX(x);
    	setY(y);
    }
    
    public void setX(float x) {
    	spr.setX(x);
    }
    
    public void setY(float y) {
    	spr.setY(y);
    }
    
	public void setVelocityX(float velX) {
		this.velX = velX;
	}
	
	public void setVelocityY(float velY) {
		this.velY = velY;
	}
	
	public float getX() {
		return spr.getX();
	}
	
	public float getY() {
		return spr.getY();
	}
	
	public float getVelocityX() {
		return velX;
	}
	
	public float getVelocityY() {
		return velY;
	}
	
	public float getWidth() {
		return spr.getWidth();
	}
	
	public float getHeight() {
		return spr.getHeight();
	}
}
