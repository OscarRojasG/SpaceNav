package com.mygdx.game.enemigos.asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.Bala;
import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;

public class Asteroide extends Enemigo {
	
    public Asteroide(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        float r = Util.generateRandomFloat(0, 1);
        this.getCuerpo().setAngularVelocity(r);
        this.setCollisionData(FiguraBits.ASTEROIDE.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit | FiguraBits.BORDE.bit | 
        														 FiguraBits.ASTEROIDE.bit | FiguraBits.BASURA_ESPACIAL.bit));
    }
    
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	
}
