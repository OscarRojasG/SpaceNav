package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FiguraSprite extends Figura {

	private Texture tx;

	public FiguraSprite(float x, float y, float ancho, float alto, Texture tx) {
		super(x,y,ancho,alto);
		this.tx = tx;
	}

	public void dibujar(SpriteBatch batch) {
    	batch.draw(tx, getX(), getY(), getAlto(), getAncho());
    }



}
