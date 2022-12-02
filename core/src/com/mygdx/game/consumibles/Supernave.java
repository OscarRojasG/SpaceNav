package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.FiguraSprite;
import com.mygdx.game.nave.Nave;

public class Supernave extends FiguraSprite implements Consumible {
	private final static float ancho =  35f/28;
	private final static float alto = 42.24f/28;
	
	private final static Texture image = new Texture(Gdx.files.internal("supernave.png"));
	
	private long tiempoInicio;
	
	public Supernave(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, image);
		tiempoInicio = TimeUtils.millis();
		setCollisionData(FiguraBits.CONSUMIBLE.bit, FiguraBits.NAVE.bit);
		setVelocidadX(velX);
		setVelocidadY(velY);
	}
	
	@Override
	public void enColisionNave(Nave nave) {
		nave.mejorar(4);
		setDestruida(true);
	}

	@Override
	public boolean noUsado() {
		float tiempo = TimeUtils.timeSinceMillis(tiempoInicio);
		tiempo = tiempo / 1000;
		
		return (tiempo > 3);
	}
	
}