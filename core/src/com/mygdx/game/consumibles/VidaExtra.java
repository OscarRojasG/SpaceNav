package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Consumible;
import com.mygdx.game.FiguraSprite;
import com.mygdx.game.Nave;

public class VidaExtra extends FiguraSprite implements Consumible {
	private final static Texture image = new Texture(Gdx.files.internal("health.png"));
	
	private long startTime;
	private long elapsedTime;

	public VidaExtra(float x, float y, float width, float height, float velX, float velY) {
		super(x, y, width, height, image);
		setVelocidadX(velX);
		setVelocidadY(velY);
		startTime = TimeUtils.millis();
	}

	@Override
	public void agregarEfecto(Nave nave) {
		nave.agregarVida();
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
		
		return (sTime > 3); // Si no se ha usado por mas de 5 segundos
	}
	
}
