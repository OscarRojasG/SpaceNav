package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Enemigo extends FiguraForma implements Movil {
	private int puntaje;
//	private final static Sound sonidoExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
    public Enemigo(float x, float y, float ancho, float alto, float accX, float accY, int puntaje) {
    	super(x, y, ancho, alto, BodyType.DynamicBody);
        this.getCuerpo().applyForceToCenter(this.getCuerpo().getMass() * accX, this.getCuerpo().getMass()*accY, true);
        this.getCuerpo().setLinearDamping(0);
        this.getCuerpo().setAngularDamping(0);
        this.setCollisionData(FiguraBits.ENEMIGO.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit | FiguraBits.BORDE.bit | FiguraBits.ENEMIGO.bit));
    	setPuntaje(puntaje);
//    	sonidoExplosion.setVolume(1, 0.5f);
    }
    
    public abstract void agregarEfecto(Nave nave);

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

    @Override
    public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        
        setPosition(x,y);
    }
    
    @Override
	public boolean verificarColision(Figura a2) {
    	Rectangle r1 = this.getArea();
    	Rectangle r2 = a2.getArea();
    	
        if (!r1.overlaps(r2))
        	return false;
        
        float intersectionX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        float intersectionY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);
     
        if (intersectionX > intersectionY) { 	
	        if (r1.y < r2.y)
	        	setY(r1.y - intersectionY - 1);
	        else
	        	setY(r1.y + intersectionY + 1);
	        
	        setVelocidadY(getVelocidadY() * -1);
	        a2.setVelocidadY(a2.getVelocidadY() * -1);  
        }
        else {
	        if (r1.x < r2.x)
	        	this.setX(r1.x - intersectionX - 0.1f);
	        else
	        	this.setX(r1.x + intersectionX + 0.1f);
	        
	        setVelocidadX(getVelocidadX() * -1);
	        a2.setVelocidadX(a2.getVelocidadX() * -1);    	
        }
        
        return true;
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
