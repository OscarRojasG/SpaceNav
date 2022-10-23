package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bullet {
	private int velX;
	private int velY;
	private Sprite spr;
	    
    public Bullet(float x, float y, int velX, int velY, Texture tx) {
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
        this.velX = velX;
        this.velY = velY;
    }
    
    public void update() {
        spr.setPosition(spr.getX() + velX, spr.getY() + velY);
    }
    
    public boolean isOffscreen() {
        if (spr.getX() < 0 || spr.getX() + spr.getWidth() > Gdx.graphics.getWidth()) 
            return true;
        
        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight())
        	return true;
        
        return false;
    }
    
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    
    public boolean existeColision(Asteroide asteroide) {
        return getArea().overlaps(asteroide.getArea());
    }
}
