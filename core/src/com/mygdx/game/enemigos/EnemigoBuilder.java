package com.mygdx.game.enemigos;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enemigos.Asteroides.Asteroide;

public interface EnemigoBuilder
{
    void setPosicion(Vector2 pos);
    void setVelocidad(Vector2 vel);
    void setPorte(float porte);
    void setPuntaje(int puntaje);
}