package com.mygdx.game.enemigos.basuraEspacial;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.EnemigoBuilder;

public class BasuraBuilder implements EnemigoBuilder {
	
    private Vector2 posicion;
    private Vector2 velocidad;
    private float porte;
    private int puntaje;

    @Override
    public void setPosicion(Vector2 pos) {
        this.posicion = pos;
    }

    @Override
    public void setVelocidad(Vector2 vel) {
        this.velocidad = vel;
    }

    @Override
    public void setPorte(float porte) {
        this.porte = porte;
    }
    
    @Override
    public void setPuntaje(int puntaje) {
    	this.puntaje = puntaje;
    }
    
    public BasuraEspacial build() {
        return new BasuraEspacial(
                this.posicion.x,
                this.posicion.y,
                this.porte - (10.0f/b2Modelo.getScale()), //Ancho
                this.porte,
                this.velocidad.x,
                this.velocidad.y,
                this.puntaje //tmp
                );
    }
}
