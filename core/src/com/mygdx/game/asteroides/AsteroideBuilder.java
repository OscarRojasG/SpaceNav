package com.mygdx.game.asteroides;

import com.badlogic.gdx.math.Vector2;

public class AsteroideBuilder implements IAsteroideBuilder {

    private AsteroideTipo tipo;
    private Vector2 posicion;
    private Vector2 velocidad;
    private float porte;

    @Override
    public void setTipo(AsteroideTipo t) {
        this.tipo = t;

    }

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

    public Asteroide build() {
        return new Asteroide(
                this.posicion.x,
                this.posicion.y,
                this.velocidad.x,
                this.velocidad.y,
                this.tipo,
                1f //tmp
                );
    }
}
