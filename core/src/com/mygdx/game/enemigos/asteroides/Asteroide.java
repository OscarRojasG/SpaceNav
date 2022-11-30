package com.mygdx.game.enemigos.asteroides;

import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.Util;
import com.mygdx.game.naves.Nave;

public class Asteroide extends Enemigo {
	private boolean generaConsumible = true;
	
    public Asteroide(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        float r = Util.generateRandomFloat(0, 1);
        this.getCuerpo().setAngularVelocity(r);
        this.setCollisionData(FiguraBits.ASTEROIDE.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit | FiguraBits.BORDE.bit | 
        														 FiguraBits.ASTEROIDE.bit));
    }
    
    public void setGeneraConsumible(boolean b) {
    	this.generaConsumible = b;
    }
    
    public boolean getGeneraConsumible() {
    	return generaConsumible;
    }
    
    @Override
	public void enColisionNave(Nave nave) {
		generaConsumible = false;
    	super.enColisionNave(nave);
	}
	
}
