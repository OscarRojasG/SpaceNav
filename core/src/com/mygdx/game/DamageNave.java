package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public abstract class DamageNave extends ObjetoEspacial {
	private final static Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));

    public DamageNave(float x, float y, int ancho, int alto, float velX, float velY, Texture tx) {
    	super(x, y, ancho, alto, velX, velY, tx);
    }
    
    public abstract boolean verificarColision(ObjetoEspacial a2);
    
    public abstract boolean verificarColision(Bala bala);
    
    public void destruir() {
    	sonidoExplosion.play();
    }
}
