package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public abstract class Asteroide extends FiguraSprite implements Movil {
	private final static Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));

    public Asteroide(float x, float y, int ancho, int alto, float velX, float velY, Texture tx) {
    	super(x, y, ancho, alto, tx);
    	setVelocidadX(velX);
    	setVelocidadY(velY);
    	sonidoExplosion.setVolume(1, 0.5f);
    }
    
    public abstract boolean verificarColision(Asteroide a2);
    
    public abstract boolean verificarColision(Bala bala);
    
    public void explotar() {
    	sonidoExplosion.play();
    }

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
        	setVelocidadY(getVelocidadY() * -1);
        }
        else if (y < 0) {
        	y = 0;
        	setVelocidadY(getVelocidadY() * -1);
        }
        
        setPosition(x,y);
    }
}
