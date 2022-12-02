package com.mygdx.game.nave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Bala;

public class DisparoNaveComun implements DisparoNave {
	private final static float anchoBala = 0.2f;
	private final static float altoBala = 0.2f;
	private final static float velBala = 50f;
	
	private Nave nave;

	public DisparoNaveComun(Nave nave) {
		this.nave = nave;
	}
	
	@Override
	public Bala disparar() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.Z) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
	        float x = nave.getX() - nave.getAlto()/2 * (float)Math.sin(nave.getAngulo()); 
	        float y = nave.getY() + nave.getAlto()/2 * (float)Math.cos(nave.getAngulo()); 
	        
	        nave.getSonidoDisparo().play(0.01f, 1, 0);
	    	
	    	return new Bala(x, y, anchoBala, altoBala, velBala, nave.getAngulo());
		}
		return null;
	}
	
}
