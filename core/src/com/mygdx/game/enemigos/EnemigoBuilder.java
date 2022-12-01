package com.mygdx.game.enemigos;

import com.badlogic.gdx.math.Vector2;

public interface EnemigoBuilder
{
	/** Recibe y guarda Vector2 de posición del Enemigo */
    void setPosicion(Vector2 pos);
    
    /** Recibe y guarda Vector2 de velocidad del Enemigo */
    void setVelocidad(Vector2 vel);
    
    /** Recibe y guarda porte del modelo que pertenece a Enemigo */
    void setPorte(float porte);
    
    /** Recibe y guarda puntaje que otroga el Enemigo */
    void setPuntaje(int puntaje);
}