package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemigo extends FiguraSprite implements Movil {
	private int puntaje;
//	private final static Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
    public Enemigo(float x, float y, float ancho, float alto, float velX, float velY, int puntaje, Texture tx) {
    	super(x, y, ancho, alto, tx);
    	setVelocidadX(velX);
    	setVelocidadY(velY);
    	setPuntaje(puntaje);
//    	sonidoExplosion.setVolume(1, 0.5f);
    }
    
    public abstract void agregarEfecto(Nave nave);
    
    @Override
    public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        
        setPosition(x,y);
    }
    
    @Override
	public boolean verificarColision(Figura a2) {
    	Rectangle r1 = this.getArea();
    	Rectangle r2 = a2.getArea();
    	
        if (!r1.overlaps(r2))
        	return false;
        
        float intersectionX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        float intersectionY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);
     
        if (intersectionX > intersectionY) { 	
	        if (r1.y < r2.y)
	        	setY(r1.y - intersectionY - 1);
	        else
	        	setY(r1.y + intersectionY + 1);
	        
	        setVelocidadY(getVelocidadY() * -1);
	        a2.setVelocidadY(a2.getVelocidadY() * -1);  
        }
        else {
	        if (r1.x < r2.x)
	        	this.setX(r1.x - intersectionX - 0.1f);
	        else
	        	this.setX(r1.x + intersectionX + 0.1f);
	        
	        setVelocidadX(getVelocidadX() * -1);
	        a2.setVelocidadX(a2.getVelocidadX() * -1);    	
        }
        
        return true;
	}
    
    public void explotar() {
//    	sonidoExplosion.play();
    }
    
    public void setPuntaje(int puntaje) {
    	this.puntaje = puntaje;
    }
    
    public int getPuntaje() {
    	return puntaje;
    }
    
}
