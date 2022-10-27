package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bala;
import com.mygdx.game.DamageNave;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;
import com.mygdx.game.Util;

public class MediumAsteroid extends DamageNave{
	private final static int ancho = 55;
	private final static int alto = 50;
	private final static Texture image = new Texture(Gdx.files.internal("aGreyMedium4.png"));
	
    public MediumAsteroid(float velX, float velY) {
    	super(Util.generateRandomInt(0, Gdx.graphics.getWidth() - ancho),
    		  Util.generateRandomInt(0, Gdx.graphics.getHeight() - alto), 
    		  ancho, alto, velX, velY, image);
    }
    
    @Override
    public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();

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
        	reverseVelY();
        }
        else if (y < 0) {
        	y = 0;
        	reverseVelY();
        }
        
        setPosition(x,y);
    }
    
	public boolean verificarColision(ObjetoEspacial a2) {
    	Rectangle r1 = this.getArea();
    	Rectangle r2 = a2.getArea();
    	
        if (!r1.overlaps(r2))
        	return false;
        
        float intersectionX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        float intersectionY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);
     
        if (intersectionX > intersectionY) { 	
	        if (r1.y < r2.y)
	        	setY(r1.y - intersectionY - 0.1f);
	        else
	        	setY(r1.y + intersectionY + 0.1f);
	        
	        reverseVelY();
	        a2.reverseVelY();
        }
        else {
	        if (r1.x < r2.x)
	        	this.setX(r1.x - intersectionX - 0.1f);
	        else
	        	this.setX(r1.x + intersectionX + 0.1f);
	        
	        setVelocidadX(getVelocidadX() * -1);
	        a2.setVelocidadX(getVelocidadX() * -1); 	
        }
        
        return true;
	}
	
	public boolean verificarColision(Bala bala) {
		return this.getArea().overlaps(bala.getArea());
	}

}
