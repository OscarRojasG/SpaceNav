package com.mygdx.game.enemigos;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;

public interface IEnemigoBuilder
{
    /** Recibe y guarda porte del modelo que pertenece a Enemigo */
    public void setPorte();
    
    /** Recibe y guarda puntaje que otroga el Enemigo */
    public void setPuntaje();

    public void setColor();

    
    /** Retorna el enemigo construido */
    public Enemigo build();
}
