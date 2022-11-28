package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class BordePantalla {

    public BordePantalla() {
    	float s = b2Modelo.getScale();
    	
        new BordeLateral(Gdx.graphics.getWidth()/(2*s), -Gdx.graphics.getHeight()/(2*s),
        				 0, Gdx.graphics.getHeight() * s);
        new BordeLateral(-Gdx.graphics.getWidth()/(2*s), -Gdx.graphics.getHeight()/(2*s),
						 0, Gdx.graphics.getHeight() * s);
        new BordeLateral(-Gdx.graphics.getWidth()/(2*s), -Gdx.graphics.getHeight()/(2*s),
						 Gdx.graphics.getWidth() * s, 0);
        new BordeLateral(-Gdx.graphics.getWidth()/(2*s), Gdx.graphics.getHeight()/(2*s),
        				 Gdx.graphics.getWidth() * s, 0);
    }
    
}

