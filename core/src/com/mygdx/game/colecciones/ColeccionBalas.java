package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Bala;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Movil;

public class ColeccionBalas extends ColeccionMovil {
	
	public void dibujar(ShapeRenderer sr) {
		Iterator<Movil> balas = getObjetos(); 
		while(balas.hasNext()) {
			Bala bala = (Bala) balas.next();
			bala.dibujar(sr);
		}
	}

	public boolean verificarColisiones(Enemigo enemigo) {
		Iterator<Movil> balas = getObjetos();
		while(balas.hasNext()) {
			Bala bala = (Bala) balas.next();
			if (bala.verificarColision(enemigo)) {
				eliminar(bala);
				return true;
			}
		}
		return false;
	}
	
}
