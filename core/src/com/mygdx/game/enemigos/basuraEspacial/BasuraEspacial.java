package com.mygdx.game.enemigos.basuraEspacial;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;

public class BasuraEspacial extends Enemigo {
	
    public BasuraEspacial(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        this.getCuerpo().setAngularVelocity(5.0f);
        this.setCollisionData(FiguraBits.BASURA_ESPACIAL.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit));
    }

    @Override
    public void dibujar(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
        sr.setColor(this.getColor());
        sr.identity();
        sr.translate(this.getXEscala(), this.getYEscala(), 0);
 		sr.rotate(0.0f, 0.0f, 1.0f, (float)Math.toDegrees(getCuerpo().getAngle()));
        sr.line(
                -this.getAnchoEscala()/2, -this.getAltoEscala()/2,
                 this.getAnchoEscala()/2, -this.getAltoEscala()/2
                );

        sr.line(
                 -this.getAnchoEscala()/2, -this.getAltoEscala()/2,
                0, +this.getAltoEscala()/2
                );

        sr.line(
                0, +this.getAltoEscala()/2,
                +this.getAnchoEscala()/2, -this.getAltoEscala()/2
                );
        sr.end();	
    }
	
}
