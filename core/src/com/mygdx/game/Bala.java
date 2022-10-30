package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;

public class Bala extends FiguraForma implements Movil {
	private final static Texture image = new Texture(Gdx.files.internal("Rocket2.png"));
	
    public Bala(float x, float y, float ancho, float alto, float velx, float vely, float angulo,
    		float centroRotacionX, float centroRotacionY) {
    	super(x, y, ancho, alto);
    	setVelocidadX(velx);
    	setVelocidadY(vely);
    	setRotacion(angulo);
    	setCentroRotacion(centroRotacionX, centroRotacionY);
    }
    
	@Override
	public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setX(x);
        setY(y);
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
		 sr.polygon(p.getTransformedVertices());

		 sr.end();	
	}
	
}
