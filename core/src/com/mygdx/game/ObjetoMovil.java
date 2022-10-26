package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class ObjetoMovil extends Elemento2D implements Figura {
	private float velX;
	private float velY;
	
	public ObjetoMovil(int x, int y, int ancho, int alto, float velX, float velY, Texture tx) {
		super(x, y, ancho, alto);
		setVelocidadX(velX);
		setVelocidadY(velY);
	}
	
	public abstract void actualizar();
	
	public void setVelocidadX(float velX) {
		this.velX = velX;
	}
	
	public void setVelocidadY(float velY) {
		this.velY = velY;
	}
	
	public float getVelocidadX() {
		return velX;
	}
	
	public float getVelocidadY() {
		return velY;
	}
	
}
