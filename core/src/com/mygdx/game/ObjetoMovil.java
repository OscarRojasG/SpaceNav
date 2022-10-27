package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class ObjetoMovil extends Figura {
	private float velX;
	private float velY;
	
	public ObjetoMovil(float x, float y, 
					   float ancho, float alto, 
					   float velX, float velY, Texture tx) {
		super(ancho, alto, x , y, tx);
		setVelocidadX(velX);
		setVelocidadY(velY);
	}
	
	public abstract void actualizar();
	
	public void desacelerar(){
		setVelocidadX(velX-1);
		setVelocidadY(velY-1);
	}
	
	public void setVelocidadX(float velX) {
		this.velX = velX;
	}
	
	public void reverseVelX() {
		this.velX *= -1;
	}
	
	public void setVelocidadY(float velY) {
		this.velY = velY;
	}
	
	public void reverseVelY() {
		this.velY *= -1;
	}
	
	public float getVelocidadX() {
		return velX;
	}
	
	public float getVelocidadY() {
		return velY;
	}
	
}
