package com.mygdx.game.asteroides;

import com.badlogic.gdx.math.Vector2;

public interface IAsteroideBuilder
{
    void setPosicion(Vector2 pos);
    void setVelocidad(Vector2 vel);
    void setPorte(float porte);
    void setTipo(AsteroideTipo tipo);
    Asteroide build();
}
