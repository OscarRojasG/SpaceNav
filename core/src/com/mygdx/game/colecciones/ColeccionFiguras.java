package com.mygdx.game.colecciones;

import java.util.ArrayList;
import java.util.Iterator;

import com.mygdx.game.Figura;
import com.mygdx.game.b2Modelo;

public abstract class ColeccionFiguras {
	private ArrayList<Figura> figuras;
	
	public ColeccionFiguras() {
		figuras = new ArrayList<Figura>();
	}
	
	public void agregar(Figura figura) {
		figuras.add(figura);
	}
	
	public void eliminar(Figura figura) {
		figuras.remove(figura);
		b2Modelo.getModelo().eliminarCuerpo(figura);
	}

	public void eliminarDestruidos() {
		for (int i = 0; i < figuras.size(); i++) {
			Figura figura = figuras.get(i);
			
			if(figura.estaDestruida()) {
				eliminar(figura);
			}
		}
	}
	
	public int getCantidad() {
		return figuras.size();
	}
	
	public Iterator<Figura> getObjetos() {
		return figuras.iterator();
	}
	
	public boolean isEmpty() {
		return figuras.isEmpty();
	}

}
