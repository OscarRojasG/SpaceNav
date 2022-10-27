package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Figura {
	private Vector2 position;
	private Vector2 velocidad;
	private Vector2 aceleracion;

	private float width;
	private float height;
	private float angulo;
	

	public Figura(float x, float y, float width, float height) {
		this.position = new Vector2(x,y);

		this.velocidad = new Vector2(0.f,0.f);

		this.aceleracion = new Vector2(0.f,0.f);


		this.width = width;
		this.height = height;

		this.angulo = 0;
	}

    public void setPosition(float x, float y) {
    	this.position.x = x;
    	this.position.y = y;
    }
    
    public void setX(float f) {
    	this.position.x = f;
    }
    
    public void setY(float f) {
    	this.position.y = f;
    }
    
    public void setSize(float ancho, float alto) {
    	this.width = ancho; 
    	this.height = alto;
    }

    public void setRotation(float angle) {
    	this.setAngulo(angle);
    }
	
	public float getX() {
		return this.position.x;
	}
	
	public float getY() {
		return this.position.y;
	}
	
	public float getAncho() {
		return this.width;
	}
	
	public float getAlto() {
		return this.height;
	}
	
	public void setVelocidadX(float velX) {
		this.velocidad.x = velX;
	}
	
	public void setVelocidadY(float velY) {
		this.velocidad.y = velY;
	}
	
	public float getVelocidadX() {
		return this.velocidad.x;
	}
	
	public float getVelocidadY() {
		return this.velocidad.y;
	}

	public float getAceleracionX() {
		return this.aceleracion.x;
	}
	
	public float getAceleracionY() {
		return this.aceleracion.y;
	}

    public Rectangle getArea() {
    	return new Rectangle(this.position.x, this.position.y,
    			this.width, this.height);
    }

	public boolean verificarColision(Figura figura) {
		return getArea().overlaps(figura.getArea());
	}

	public float getAngulo() {
		return this.angulo;
	}

	public void setAngulo(float angulo) {
		this.angulo = angulo;
	}



}

