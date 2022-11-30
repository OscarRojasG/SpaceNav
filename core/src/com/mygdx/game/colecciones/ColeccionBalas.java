package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Bala;
import com.mygdx.game.Figura;

public class ColeccionBalas extends ColeccionFiguras {
	
	public void dibujar(ShapeRenderer sr) {
        Iterator<Figura> balas = this.getObjetos();
        while(balas.hasNext()) {
            Bala bala = (Bala) balas.next();
            bala.dibujar(sr);
        }
	}

}
