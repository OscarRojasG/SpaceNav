package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FiguraSprite extends Elemento2D implements Figura {

	private Texture tx;

	public FiguraSprite(int x, int y, int ancho, int alto, Texture tx) {
		this.tx = tx;
		setSize(ancho, alto);
		setX(x);
		setY(y);
	}

	@Override
	public void dibujar(SpriteBatch batch) {
    	batch.draw(tx, getX(), getY(), getAlto(), getAncho());
    }


}
