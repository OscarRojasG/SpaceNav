package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Asteroide {
    private float velX;
    private float velY;
    private Sprite spr;

    public Asteroide(int x, int y, int size, float velX, float velY, Texture tx) {
    	spr = new Sprite(tx);
        spr.setPosition(x, y);
        spr.setSize(size, size);
        
        this.setVelocityX(velX);
        this.setVelocityY(velY);
    }
    
    public void update() {
        float x = spr.getX() + getVelocityX() * Gdx.graphics.getDeltaTime();
        float y = spr.getY() + getVelocityY() * Gdx.graphics.getDeltaTime();

        if (x + spr.getWidth() > Gdx.graphics.getWidth()) {
        	x = Gdx.graphics.getWidth() - spr.getWidth();
        	velX *= -1;
        }
        else if (x < 0) {
        	x = 0;
        	velX *= -1;
        }
        
        if (y + spr.getHeight() > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - spr.getHeight();
        	velY *= -1;
        }
        else if (y < 0) {
        	y = 0;
        	velY *= -1;
        }
        
        spr.setPosition(x, y);
    }
    
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
    public void checkCollision(Asteroide a2) {
    	Rectangle r1 = this.getArea();
    	Rectangle r2 = a2.getArea();
    	
        if (!r1.overlaps(r2)) return;
        
        float intersectionX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        float intersectionY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);
     
        if (intersectionX > intersectionY) { 	
	        if (r1.y < r2.y)
	        	this.setY(r1.y - intersectionY - 0.1f);
	        else
	        	this.setY(r1.y + intersectionY + 0.1f);
	        
	        velY *= -1;
	        a2.setVelocityY(a2.getVelocityY() * -1);  
        }
        else {
	        if (r1.x < r2.x)
	        	this.setX(r1.x - intersectionX - 0.1f);
	        else
	        	this.setX(r1.x + intersectionX + 0.1f);
	        
	        velX *= -1;
	        a2.setVelocityX(a2.getVelocityX() * -1);    	
        }
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
	
	public float getVelocityX() {
		return velX;
	}
	
	public float getVelocityY() {
		return velY;
	}
}
