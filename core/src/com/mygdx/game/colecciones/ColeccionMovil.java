package com.mygdx.game.colecciones;

import java.util.ArrayList;
import java.util.Iterator;

import com.mygdx.game.Figura;
import com.mygdx.game.Movil;
import com.mygdx.game.Nave;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.asteroides.Asteroide;

public class ColeccionMovil {
	private ArrayList<Movil> moviles;
	
	public ColeccionMovil() {
		moviles = new ArrayList<Movil>();
	}
	
	public void agregar(Movil movil) {
		moviles.add(movil);
	}
	
	public void eliminar(Movil movil) {
		moviles.remove(movil);
		b2Modelo.getModelo().eliminarCuerpo((Figura) movil);
	}

	public void eliminarDestruidos() {
		for (int i = 0; i < moviles.size(); i++) {
			Movil movil = moviles.get(i);
			
			if(((Figura) movil).estaDestruida()) {
				eliminar(movil);
			}
		}
	}
	
	public int getCantidad() {
		return moviles.size();
	}
	
	public Iterator<Movil> getObjetos() {
		return moviles.iterator();
	}
	
	public boolean isEmpty() {
		return moviles.isEmpty();
	}

}
