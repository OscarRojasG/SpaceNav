package com.mygdx.game.nave;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Bala;

public class DisparoNaveMejorada extends DisparoNave {
	private final static float anchoBala = 0.4f;
	private final static float altoBala = 0.4f;
	private final static float velBala = 70f;
	private final static float velDisparo = 8.5f;
	
    private float tiempoUltimoDisparo;

	public DisparoNaveMejorada(Nave nave) {
		super(nave);
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
		Nave nave = getNave();
        float x = nave.getX() - nave.getAlto()/2 * (float)Math.sin(nave.getAngulo()); 
        float y = nave.getY() + nave.getAlto()/2 * (float)Math.cos(nave.getAngulo()); 
    	
    	return new Bala(x, y, anchoBala, altoBala, velBala, nave.getAngulo());
    }
	
	@Override
	public void reproducirSonidoDisparo() {
		getSonidoDisparo().play(0.01f, 2, 0);
	}
	
}
