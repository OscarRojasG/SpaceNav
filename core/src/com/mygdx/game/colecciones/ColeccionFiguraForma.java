package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Figura;
import com.mygdx.game.FiguraForma;

public class ColeccionFiguraForma extends ColeccionFiguras {
	
	/** Dibuja el modelo de cada figura presente en la coleccion */
	public void dibujar(ShapeRenderer sr) {
        Iterator<Figura> figuras = this.getObjetos();
        while(figuras.hasNext()) {
            FiguraForma ff = (FiguraForma) figuras.next();
            ff.dibujar(sr);
        }
	}
}
