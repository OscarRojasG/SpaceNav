package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class FiguraForma extends Figura {

	public FiguraForma(float x, float y, float width, float height, BodyType bt) {
		super(x, y, width, height, bt);
	}

	public FiguraForma(float x, float y, float width, float height) {
		super(x, y, width, height, BodyType.StaticBody);
	}
	
	/** Dibuja la forma de la Figura en pantalla */
	public abstract void dibujar(ShapeRenderer sr);
	
}
