package com.mygdx.game.enemigos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;

public abstract class EnemigoBuilder {
    private Vector2 velocidad; 
    private Vector2 posicion;
    private Color color;

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    } 

    public void setVelocidad(Vector2 velocidad) {
        this.velocidad = velocidad;
    }

    public void setColor() {
        this.color = new Color(180/255f, 180/255f, 180/255f, 0);
    }

    public Vector2 getPosicion() {
        return this.posicion;
    }

    public Vector2 getVelocidad() {
        return this.velocidad;
    }

    public Color getColor() {
        return color;
    }

	public abstract void setPorte();
	
	public abstract void setPuntaje();

	public abstract Enemigo build();

}
