package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public abstract class Asteroide extends ObjetoEspacial {
	private final static Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));

    public Asteroide(float x, float y, int ancho, int alto, float velX, float velY, Texture tx) {
    	super(x, y, ancho, alto, velX, velY, tx);
    	sonidoExplosion.setVolume(1, 0.5f);
    }
    
    public abstract boolean verificarColision(Asteroide a2);
    
    public abstract boolean verificarColision(Bala bala);
    
    public void destruir() {
    	sonidoExplosion.play();
    }
}
