package com.mygdx.game.enemigos.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.Util;
import com.mygdx.game.nave.Nave;

public class Asteroide extends Enemigo {
	private boolean generaConsumible = true;
	private static final Sound sonidoExplosion = Gdx.audio.newSound(
			Gdx.files.internal("explosionAsteroide.ogg"));
	
    public Asteroide(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        float r = Util.generateRandomFloat(0, 1);
        this.getCuerpo().setAngularVelocity(r);
        this.setCollisionData(FiguraBits.ASTEROIDE.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit | FiguraBits.BORDE.bit | 
        														 FiguraBits.ASTEROIDE.bit));
    }
    
    /** Guarda boolean recibido
     * @param b Es un Boolean que decide si se genera o no Consumible */
    public void setGeneraConsumible(boolean b) {
    	this.generaConsumible = b;
    }
    /** @return generarConsumible Es un Boolean que decide si se genera o no Consumible */
    public boolean getGeneraConsumible() {
    	return generaConsumible;
    }

	@Override
	public void agregarEfecto(Nave nave) {
		nave.herir();
	}

	@Override
	public void explotar() {
		sonidoExplosion.play(0.03f);
	}
	
}
