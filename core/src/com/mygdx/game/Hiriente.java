package com.mygdx.game;

public interface Hiriente{
	/** Clase usada para aplicar un efecto al colicionar con la nave
	 * @param nave: Recibe atributo de clase Nave para manipularlo
	 * */
	public abstract void agregarEfecto(Nave nave);
	
	/** Se encarga de enviar el cambio de puntaje que sucede cuando la nave lo colisiona.
	 * @return int: Puntaje a agregar o quitar en el juego.
	 * */
	public int getScoreChange();
}
