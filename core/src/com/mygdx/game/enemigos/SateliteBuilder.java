
package com.mygdx.game.enemigos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.basuraEspacial.BasuraEspacial;

public class SateliteBuilder extends EnemigoBuilder {
    private Color color;
    private float porte;
    private int puntaje;

    @Override
    public void setColor() {
        this.color = Color.ORANGE; 
    }

    @Override
    public void setPorte() {
        this.porte = 50/b2Modelo.getScale();
    }

    @Override
    public void setPuntaje() {
        this.puntaje = 25;
    }

    @Override
    public Enemigo build() {
        Vector2 p = this.getPosicion();
        Vector2 v = this.getVelocidad();
        Enemigo e = new BasuraEspacial(
                p.x,
                p.y,
                this.porte,
                this.porte * (float)Math.sqrt(3)/2,
                v.x,
                v.y,
                this.puntaje
                );
        e.setColor(color);
        return e;
    }
}
