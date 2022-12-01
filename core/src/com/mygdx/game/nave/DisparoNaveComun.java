package com.mygdx.game.nave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Bala;

public class DisparoNaveComun extends DisparoNave {
	private final static float anchoBala = 0.2f;
	private final static float altoBala = 0.2f;
	private final static float velBala = 50f;

	public DisparoNaveComun(Nave nave) {
		super(nave);
	}

	@Override
	public boolean puedeDisparar() {
		return Gdx.input.isKeyJustPressed(Input.Keys.Z) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
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
		getSonidoDisparo().play(0.01f, 1, 0);
	}

}
