package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Enemigo extends FiguraForma implements Movil, NaveColisionable, BalaColisionable {
	private int puntaje;
//	private final static Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
    public Enemigo(float x, float y, float ancho, float alto, float accX, float accY, int puntaje) {
    	super(x, y, ancho, alto, BodyType.DynamicBody);
        this.getCuerpo().applyForceToCenter(this.getCuerpo().getMass() * accX, this.getCuerpo().getMass()*accY, true);
        this.getCuerpo().setLinearDamping(0);
        this.getCuerpo().setAngularDamping(0);
    	setPuntaje(puntaje);
//    	sonidoExplosion.setVolume(1, 0.5f);
    }

    @Override
    public void dibujar(ShapeRenderer sr) {
        Polygon p = this.getPoligono();
        sr.begin(ShapeType.Line);
        sr.setColor(0xff, 0xff, 0xff, 1);
        sr.identity();
		// sr.translate(getOrigenX(), getOrigenY(), 0);
        sr.polygon(p.getTransformedVertices());

        sr.end();	
    }
    
    public void explotar() {
//    	sonidoExplosion.play();
    }
    
    public void setPuntaje(int puntaje) {
    	this.puntaje = puntaje;
    }
    
    public int getPuntaje() {
    	return puntaje;
    }
    
}
