package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bala extends FiguraSprite implements Movil {
	private final static Texture image = new Texture(Gdx.files.internal("Rocket2.png"));
	
    public Bala(float x, float y, float ancho, float alto, float velx, float vely, float angulo) {
    	super(x, y, ancho, alto, image);
    	setVelocidadX(velx);
    	setVelocidadY(vely);
    	setAngulo(angulo);
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
	
	/** Clase para verificar colisión entre Enemigo y Bala.
	 * @param Enemigo: Un parametro clase Enemigo que contiene su posición y área
	 * @return boolean: Manda true si la posición de Enemigo coincide con el área de la bala. False sino.
	 * */
	public boolean verificarColision(Enemigo asteroide) {
		return this.getArea().overlaps(asteroide.getArea());
	}
	
	@Override
	public void dibujar(SpriteBatch batch) {
		Sprite sprite = getSprite();
		sprite.setPosition(getX(), getY());
		sprite.setCenter(getX(), getY());
		sprite.setRotation(getAngulo());	
		sprite.draw(batch);
	}
	
}
