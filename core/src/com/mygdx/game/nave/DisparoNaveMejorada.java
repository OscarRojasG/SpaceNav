package com.mygdx.game.nave;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Bala;

public class DisparoNaveMejorada implements DisparoNave {
	private final static float anchoBala = 0.4f;
	private final static float altoBala = 0.4f;
	private final static float velBala = 70f;
	private final static float velDisparo = 8.5f;
	
	private Nave nave;
    private float tiempoUltimoDisparo;

	public DisparoNaveMejorada(Nave nave) {
		this.nave = nave;
		this.tiempoUltimoDisparo = 0;
	}
	
	@Override
	public Bala disparar() {
		tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();
		
		if (tiempoUltimoDisparo > (1 / velDisparo)) {
			tiempoUltimoDisparo -= (1 / velDisparo);
		
	        float x = nave.getX() - nave.getAlto()/2 * (float)Math.sin(nave.getAngulo()); 
	        float y = nave.getY() + nave.getAlto()/2 * (float)Math.cos(nave.getAngulo()); 
	        
	        nave.getSonidoDisparo().play(0.01f, 2, 0);
	    	
	    	return new Bala(x, y, anchoBala, altoBala, velBala, nave.getAngulo());
		}

		return null;
	}
	
}
