package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.FiguraSprite;
import com.mygdx.game.nave.Nave;

public class VidaExtra extends FiguraSprite implements Consumible {
	private final static float ancho = 1.3f;
	private final static float alto = 1.3f;
	
	private final static Texture image = new Texture(Gdx.files.internal("health.png"));
	
	private long tiempoInicio;
	
	public VidaExtra(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, image);
		tiempoInicio = TimeUtils.millis();
		setCollisionData(FiguraBits.CONSUMIBLE.getBit(), FiguraBits.NAVE.getBit());
		setVelocidadX(velX);
		setVelocidadY(velY);
	}

	@Override
	public void enColisionNave(Nave nave) {
		nave.agregarVida();
		setDestruida(true);
	}

	@Override
	public boolean noUsado() {
		float tiempo = TimeUtils.timeSinceMillis(tiempoInicio);
		tiempo = tiempo / 1000;
		
		return (tiempo > 5);
	}
	
}
