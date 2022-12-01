package com.mygdx.game;

import com.mygdx.game.nave.Nave;

public interface NaveColisionable {
	/** Aplica acciones correspondientes al colisionar 
	 * la Nave recibida y el objeto donde aplica el metodo 
	 * */
	public void enColisionNave(Nave nave);
}
