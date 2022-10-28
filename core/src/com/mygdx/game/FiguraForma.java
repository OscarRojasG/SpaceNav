package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class FiguraForma extends Figura {

	public FiguraForma(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	/** Dibuja la forma de la Figura en pantalla */
	public abstract void dibujar(ShapeRenderer sr);
	
}
