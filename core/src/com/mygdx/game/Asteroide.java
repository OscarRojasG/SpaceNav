package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Asteroide extends ObjetoEspacial {

    public Asteroide(float x, float y, int size, float velX, float velY, Texture tx) {
    	super(x, y, velX, velY, size, size, tx);
    }
    
    @Override
    public void update() {
        float x = getX() + getVelocityX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocityY() * Gdx.graphics.getDeltaTime();

        if (x + getWidth() > Gdx.graphics.getWidth()) {
        	x = Gdx.graphics.getWidth() - getWidth();
        	setVelocityX(getVelocityX() * -1);
        }
        else if (x < 0) {
        	x = 0;
        	setVelocityX(getVelocityX() * -1);
        }
        
        if (y + getHeight() > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - getHeight();
        	setVelocityY(getVelocityY() * -1);
        }
        else if (y < 0) {
        	y = 0;
        	setVelocityY(getVelocityY() * -1);
        }
        
        setPosition(x,y);
    }
    
    public void revisarColision(Asteroide a2) {
    	Rectangle r1 = this.getArea();
    	Rectangle r2 = a2.getArea();
    	
        if (!r1.overlaps(r2)) return;
        
        float intersectionX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        float intersectionY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);
     
        if (intersectionX > intersectionY) { 	
	        if (r1.y < r2.y)
	        	setY(r1.y - intersectionY - 0.1f);
	        else
	        	setY(r1.y + intersectionY + 0.1f);
	        
	        setVelocityY(getVelocityY() * -1);
	        a2.setVelocityY(a2.getVelocityY() * -1);  
        }
        else {
	        if (r1.x < r2.x)
	        	this.setX(r1.x - intersectionX - 0.1f);
	        else
	        	this.setX(r1.x + intersectionX + 0.1f);
	        
	        setVelocityX(getVelocityX() * -1);
	        a2.setVelocityX(a2.getVelocityX() * -1);    	
        }
    }
    
    @Override
	public void enColision(Nave4 nave) {
		nave.quitarVida();
	}
}
