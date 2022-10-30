package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FiguraSprite extends Figura {
	private Sprite sprite;

	public FiguraSprite(float x, float y, float ancho, float alto, Texture tx) {
		super(x,y,ancho,alto);
		sprite = new Sprite(tx);
	}
	
	/** Dibuja el sprite de la Figura en pantalla sobre su forma .*/
	public void dibujar(SpriteBatch batch) {
		sprite.setPosition(getX(), getY());
		sprite.setSize(getAncho(), getAlto());
		sprite.setOrigin(getCentroRotacionX(), getCentroRotacionY());
		sprite.setRotation(getRotacion());	
		sprite.draw(batch);
    }
	
}
