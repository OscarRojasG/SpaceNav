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
	
    public Bala disparar() {
    	if (puedeDisparar() && !nave.estaHerida()) {
    		reproducirSonidoDisparo();
    		return generarBala();
    	}
    	return null;
    }
    
    public Nave getNave() {
    	return nave;
    }
    
    public Sound getSonidoDisparo() {
    	return sonidoDisparo;
    }
	
	public abstract boolean puedeDisparar();
	public abstract void reproducirSonidoDisparo();
	public abstract Bala generarBala();
}
