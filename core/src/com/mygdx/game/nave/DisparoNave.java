package com.mygdx.game.nave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Bala;

public abstract class DisparoNave {
	private static final Sound sonidoDisparo = Gdx.audio.newSound(Gdx.files.internal("disparoNave.mp3"));
	private Nave nave;
	
	public DisparoNave(Nave nave) {
		this.nave = nave;
	}
	
	/** @return Bala Es la Bala disparada (si no dispara retorna null) */
    public Bala disparar() {
    	if (puedeDisparar() && !nave.estaHerida()) {
    		reproducirSonidoDisparo();
    		return generarBala();
    	}
    	return null;
    }
    
    /** @return Nave Es la nave que dispara */
    public Nave getNave() {
    	return nave;
    }
    
    /** @return Sound Es el sonido que correspone a disparar */
    public Sound getSonidoDisparo() {
    	return sonidoDisparo;
    }
	
    /** @return boolean Es un true si se presiona tecla para disparar y false en caso contrario */
	public abstract boolean puedeDisparar();
	
	/** Reproduce el sonido de disparo con los ajustes correspondientes */
	public abstract void reproducirSonidoDisparo();
	
	/** Genera una Bala respecto a Nave y la retorna */
	public abstract Bala generarBala();
}
