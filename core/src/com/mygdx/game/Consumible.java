package com.mygdx.game;

public interface Consumible extends Movil {
	public void agregarEfecto(Nave nave);
	
	public boolean noUsado();
}
