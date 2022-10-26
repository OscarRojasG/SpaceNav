package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bala extends ObjetoMovil {
	private final static Texture image = new Texture(Gdx.files.internal("Rocket2.png"));
	
    public Bala(float x, float y, float ancho, float alto, float velx, float vely) {
    	super(x, y, ancho, alto, velx, vely, image);
    }
    
	@Override
	public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setX(x);
        setY(y);
	}
	
	public boolean verificarColision(Asteroide asteroide) {
		return this.getArea().overlaps(asteroide.getArea());
	}
	
}
