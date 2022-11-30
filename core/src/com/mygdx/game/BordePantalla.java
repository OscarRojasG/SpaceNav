package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class BordePantalla {
	private BordeLateral b1, b2, b3, b4;

    public BordePantalla() {
    	float s = b2Modelo.getScale();
    	
        b1 = new BordeLateral(Gdx.graphics.getWidth()/(2*s), -Gdx.graphics.getHeight()/(2*s),
        				 0, Gdx.graphics.getHeight() * s);
        b2 = new BordeLateral(-Gdx.graphics.getWidth()/(2*s), -Gdx.graphics.getHeight()/(2*s),
						 0, Gdx.graphics.getHeight() * s);
        b3 = new BordeLateral(-Gdx.graphics.getWidth()/(2*s), -Gdx.graphics.getHeight()/(2*s),
						 Gdx.graphics.getWidth() * s, 0);
        b4 = new BordeLateral(-Gdx.graphics.getWidth()/(2*s), Gdx.graphics.getHeight()/(2*s),
        				 Gdx.graphics.getWidth() * s, 0);
    }
    
	public void eliminar() {
		b2Modelo.getModelo().eliminarCuerpo(b1);
		b2Modelo.getModelo().eliminarCuerpo(b2);
		b2Modelo.getModelo().eliminarCuerpo(b3);
		b2Modelo.getModelo().eliminarCuerpo(b4);
	}
    
}

