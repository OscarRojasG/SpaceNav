package com.mygdx.game.enemigos;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;

public interface EnemigoBuilder
{
	/** Recibe y guarda Vector2 de posición del Enemigo */
    public void setPosicion(Vector2 pos);
    
    /** Recibe y guarda Vector2 de velocidad del Enemigo */
    public void setVelocidad(Vector2 vel);
    
    /** Recibe y guarda porte del modelo que pertenece a Enemigo */
    public void setPorte(float porte);
    
    /** Recibe y guarda puntaje que otroga el Enemigo */
    public void setPuntaje(int puntaje);
    
    /** Retorna el enemigo construido */
    public Enemigo build();
}