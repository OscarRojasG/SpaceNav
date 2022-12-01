package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.mygdx.game.Figura;
import com.mygdx.game.Util;
import com.mygdx.game.consumibles.Consumible;
import com.mygdx.game.consumibles.Supernave;
import com.mygdx.game.consumibles.VidaExtra;


public class ColeccionConsumibles extends ColeccionFiguraSprite {
	private final static int CONSUMIBLE_VIDA = 1;
	private final static int CONSUMIBLE_SUPERNAVE = 2;
	
	@Override
	public void eliminarDestruidos() {
		Iterator<Figura> consumibles = getObjetos(); 
		while(consumibles.hasNext()) {
			Consumible consumible = (Consumible) consumibles.next();			
			if (consumible.noUsado() || consumible.estaDestruida()) {
				consumibles.remove();
				eliminar(consumible);
			}	
		}
	}
	
	/** Genera un elemento Consumible con los datos recibidos 
	 * @param x Posicion en el eje X
	 * @param y Posicion en el eje Y
	 * @param velX Velocidad en el eje X
	 * @param velY Velocidad en el eje Y
	 * */
	public void generar(float x, float y,
						float velX, float velY) {
		
		int p = Util.generateRandomInt(0, 9);
		
		if(p != 0)
			return;	
		
		int n = generarConsumibleAleatorio();
		
		Consumible consumible = null;
		
		switch(n) {
			case CONSUMIBLE_VIDA:
				consumible = new VidaExtra(x, y, velX, velY);
				break;
			case CONSUMIBLE_SUPERNAVE:
				consumible = new Supernave(x, y, velX, velY);
				break;
		}
		
		this.agregar(consumible);
	}
	
	private int generarConsumibleAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
}
