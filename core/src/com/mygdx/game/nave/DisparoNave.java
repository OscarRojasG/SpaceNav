package com.mygdx.game.nave;

import com.mygdx.game.Bala;

public interface DisparoNave {
	public boolean puedeDisparar();
	public void reproducirSonidoDisparo();
	public Bala generarBala();
}
