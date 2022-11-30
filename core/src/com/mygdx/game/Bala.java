package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Bala extends FiguraForma {
	
    public Bala(float x, float y, float ancho, float alto, float vel, float angulo) {
    	super(x, y, ancho, alto, BodyType.DynamicBody);
        this.getCuerpo().setLinearVelocity(vel * (float)-Math.sin(angulo), vel * (float)Math.cos(angulo));
        this.setCollisionData(FiguraBits.BALA.bit, (short) (FiguraBits.ASTEROIDE.bit | FiguraBits.BASURA_ESPACIAL.bit));
    }

	@Override
	public void dibujar(ShapeRenderer sr) {
        Polygon p = this.getPoligono();
        sr.begin(ShapeType.Line);
        sr.setColor(0xff, 0xff, 0xff, 1);
        sr.identity();
        sr.polygon(p.getTransformedVertices());
        sr.end();	
	}
	
}
