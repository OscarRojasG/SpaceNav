package com.mygdx.game.enemigos.asteroides;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.EnemigoBuilder;

public class AsteroideChicoBuilder extends EnemigoBuilder {

    private int puntaje;
    private float porte;

    @Override
    public void setPuntaje() {
        this.puntaje = 10;
    }

    @Override
    public void setPorte() {
        this.porte = 40/b2Modelo.getScale();
    }

    @Override
    public Enemigo build() {
        Vector2 v = this.getVelocidad();
        Vector2 p = this.getPosicion();
        Enemigo a =  new Asteroide(
                p.x, p.y, porte, porte, v.x, v.y, puntaje
            );
        a.setColor(this.getColor());
        return a;
    }

}
