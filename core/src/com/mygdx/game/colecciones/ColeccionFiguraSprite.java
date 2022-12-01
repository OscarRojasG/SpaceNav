package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Figura;
import com.mygdx.game.consumibles.Consumible;

public class ColeccionFiguraSprite extends ColeccionFiguras {
	
	/** Dibuja el sprite de la clase consumible */
	public void dibujar(SpriteBatch batch) {
		Iterator<Figura> consumibles = getObjetos(); 
		while(consumibles.hasNext()) {
			Consumible consumible = (Consumible) consumibles.next();
			consumible.dibujar(batch);
		}
	}
	
}
