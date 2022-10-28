package com.mygdx.game;

public interface Consumible extends Movil {
	/** Clase usada para aplicar un efecto al colicionar con la nave
	 * @param nave: Recibe atributo de clase Nave para manipularlo
	 * */
	public void agregarEfecto(Nave nave);
	
	/** Clase que verifica el tiempo del consumible desde su aparición.
	 * @return boolean: Retorna true si no se ha usado por cierto tiempo.
	 * */
	public boolean noUsado();
}
