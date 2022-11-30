package com.mygdx.game.naves;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Bala;

public class DisparoNaveMejorada implements DisparoNave {
	private final static float anchoBala = 0.2f;
	private final static float altoBala = 0.2f;
	private final static float velBala = 70f;
	private final static float velDisparo = 8.5f;
	
	private Nave nave;
    private float tiempoUltimoDisparo;

	public DisparoNaveMejorada(Nave nave) {
		this.nave = nave;
		this.tiempoUltimoDisparo = 0;
	}

	@Override
	public boolean puedeDisparar() {
		tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();
		
		if (tiempoUltimoDisparo > (1 / velDisparo)) {
			tiempoUltimoDisparo -= (1 / velDisparo);
			return true;
		}
		return false;
	}
	
	@Override
    public Bala generarBala() {
        float x = nave.getX() - nave.getAlto() * (float)Math.sin(nave.getAngulo()); 
        float y = nave.getY() + nave.getAlto() * (float)Math.cos(nave.getAngulo()); 
    	
    	return new Bala(x, y, anchoBala, altoBala, velBala, nave.getAngulo());
    }
	
	@Override
	public void reproducirSonidoDisparo() {
		nave.getSonidoDisparo().play(0.01f, 2, 0);
	}
	
}
