package com.mygdx.game.enemigos.basuraEspacial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.nave.Nave;

public class BasuraEspacial extends Enemigo {
	private static final Sound sonidoExplosion = Gdx.audio.newSound(
			Gdx.files.internal("explosionBasura.ogg"));
	
    public BasuraEspacial(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        this.getCuerpo().setAngularVelocity(4.0f);
        this.setFormaTriangular();
        this.setCollisionData(FiguraBits.BASURA_ESPACIAL.getBit(), (short) (FiguraBits.BALA.getBit() | FiguraBits.NAVE.getBit()));
    }

    @Override
    public void dibujar(ShapeRenderer sr) {
        sr.begin(ShapeType.Line);
        sr.setColor(this.getColor());
        sr.identity();
        sr.translate(this.getXEscala(), this.getYEscala(), 0);
 		sr.rotate(0.0f, 0.0f, 1.0f, (float)Math.toDegrees(getCuerpo().getAngle()));
        sr.line(
                -this.getAnchoEscala()/2, -this.getAltoEscala()/3,
                 this.getAnchoEscala()/2, -this.getAltoEscala()/3
                );

        sr.line(
                -this.getAnchoEscala()/2, -this.getAltoEscala()/3,
                0, 2f/3 * this.getAltoEscala()
                );

        sr.line(
                0, 2f/3 * this.getAltoEscala(),
                +this.getAnchoEscala()/2, -this.getAltoEscala()/3
                );
        sr.end();
    }

	@Override
	public void agregarEfecto(Nave nave) {
		nave.desestabilizar();
	}

	@Override
	public void explotar() {
		sonidoExplosion.play(0.06f);
	}
	
}
