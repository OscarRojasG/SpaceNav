package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Elemento2D {
	private Vector2 position;
	private float width;
	private float height;
	private float angulo;

	public Elemento2D(int x, int y, int width, int height) {
		this.position.x = x;
		this.position.y = y;
		this.width = width;
		this.height = height;
		this.angulo = 0;
	}

    public void setPosition(int x, int y) {
    	this.position.x = x;
    	this.position.y = y;
    }
    
    public void setX(int x) {
    	this.position.x = x;
    }
    
    public void setY(int y) {
    	this.position.y = y;
    }
    
    public void setSize(float ancho, float alto) {
    	this.width = ancho; this.height = alto;
    }

    public void setRotation(float angle) {
    	this.angulo = angle;
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
	
    public Rectangle getArea() {
    	return new Rectangle(this.position.x, this.position.y,
    			this.width, this.height);
    }
}
