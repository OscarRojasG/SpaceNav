package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Bala extends FiguraForma implements Movil {
	private final static Texture image = new Texture(Gdx.files.internal("Rocket2.png"));
    private final static float ACELERACION = 100.f;
	
    public Bala(float x, float y, float ancho, float alto, float velx, float vely, float angulo,
    		float centroRotacionX, float centroRotacionY) {
    	super(x, y, ancho, alto, BodyType.DynamicBody);
        this.getCuerpo().setLinearVelocity(ACELERACION * (float)-Math.sin(angulo),ACELERACION * (float)Math.cos(angulo));
    }
    
	@Override
	public void actualizar() {
        System.out.println(this.getCuerpo().getLinearVelocity().dst(0, 0));
	}
	
	/** Envia la imagen que utiliza bala en pantalla
	 * @return Texture: Image que usara la clase para aparecer en pantalla
	 */
	public Texture getImageBala() {
		return image;
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
	
}
