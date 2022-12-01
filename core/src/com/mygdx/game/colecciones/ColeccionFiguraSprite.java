package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Figura;
import com.mygdx.game.FiguraSprite;

public class ColeccionFiguraSprite extends ColeccionFiguras {
	
	/** Dibuja el sprite de la clase consumible */
	public void dibujar(SpriteBatch batch) {
		Iterator<Figura> figuras = getObjetos(); 
		while(figuras.hasNext()) {
			FiguraSprite fs = (FiguraSprite) figuras.next();
			fs.dibujar(batch);
		}
	}
	
}
