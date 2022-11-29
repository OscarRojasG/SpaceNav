package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Bala;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Movil;

public class ColeccionBalas extends ColeccionMovil {
	
	public void dibujar(ShapeRenderer sr) {
        Iterator<Movil> b = this.getObjetos();
        while(b.hasNext()) {
            Bala bb = (Bala)b.next();
            bb.dibujar(sr);
        }
	}

}
