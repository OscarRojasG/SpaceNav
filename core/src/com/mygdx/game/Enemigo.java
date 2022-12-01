package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.nave.Nave;



public abstract class Enemigo
    extends FiguraForma
    implements NaveColisionable, BalaColisionable {
	
	private int puntaje;
	private Color color;
	private final static Sound sonidoExplosion = Gdx.audio.newSound(
            Gdx.files.internal("explosion.ogg"));
	
    public Enemigo(
            float x, float y,
            float ancho, float alto,
            float accX, float accY,
            int puntaje) {

    	super(x, y, ancho, alto, BodyType.DynamicBody);

        this.getCuerpo().applyForceToCenter(
                this.getCuerpo().getMass() * accX,
                this.getCuerpo().getMass()*accY,
                true);

        this.getCuerpo().setLinearDamping(0);
        this.getCuerpo().setAngularDamping(0);

    	setPuntaje(puntaje);
    }

    @Override
    public void dibujar(ShapeRenderer sr) {
        Polygon p = this.getPoligono();
        sr.begin(ShapeType.Line);
        sr.setColor(color);
        sr.identity();
        sr.polygon(p.getTransformedVertices());
        sr.end();	
    }
    
    @Override
	public void enColisionNave(Nave nave) {
		nave.herir();
		setPuntaje(0);
		setDestruida(true);
		explotar();
	}
	
    @Override
	public void enColisionBala(Bala bala) {
		setDestruida(true);
		bala.setDestruida(true);
		explotar();
	}
    
    /** Reproduce sonido de explosión */
    public void explotar() {
    	sonidoExplosion.play(0.02f);
    }
    
    /** Guarda el color que le corresponde al Enemigo */
    public void setColor(Color color) {
    	this.color = color;
    }
    
    /** Guarda el puntaje que otorga el Enemigo */
    public void setPuntaje(int puntaje) {
    	this.puntaje = puntaje;
    }
    
    /** @return puntaje Retorna el puntaje que otorga el Enemigo  */
    public int getPuntaje() {
    	return puntaje;
    }
    
}
