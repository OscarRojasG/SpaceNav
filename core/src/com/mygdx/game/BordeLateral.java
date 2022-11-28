package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BordeLateral extends Figura {

	public BordeLateral(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, BodyType.StaticBody);
	}

}
