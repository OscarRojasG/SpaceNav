package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Consumible;
import com.mygdx.game.FiguraSprite;
import com.mygdx.game.Nave;

public class Supernave extends FiguraSprite implements Consumible {
	private final static float ancho =  35 ;
	private final static float alto = 42.24f;
	private final static Texture image = new Texture(Gdx.files.internal("supernave.png"));
	
	private long startTime;
	private long elapsedTime;
	
	public Supernave(float x, float y, float velX, float velY) {
		super(x, y, ancho, alto, image);
		setVelocidadX(velX);
		setVelocidadY(velY);
		startTime = TimeUtils.millis();
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.mejorar(5);
	}

	@Override
	public void actualizar() {
		float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setPosition(x,y);
	}
	
	@Override
	public boolean noUsado() {
		elapsedTime  = TimeUtils.timeSinceMillis(startTime);
		long sTime = elapsedTime / 1000; // Consiguiendolo en segundos
		
		return (sTime > 6); // Si no se ha usado por mas de 6 segundos
	}
	
}