package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Movil;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.mygdx.game.consumibles.Consumible;
import com.mygdx.game.consumibles.Supernave;
import com.mygdx.game.consumibles.VidaExtra;

public class ColeccionConsumibles extends ColeccionMovil {
	private final static int CONSUMIBLE_VIDA = 1;
	private final static int CONSUMIBLE_SUPERNAVE = 2;
	
	public void generar(float x, float y, float velX, float velY) {
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
	
	public void dibujar(SpriteBatch batch) {
		Iterator<Movil> consumibles = getObjetos(); 
		while(consumibles.hasNext()) {
			Consumible consumible = (Consumible) consumibles.next();
			consumible.dibujar(batch);
		}
	}
	
	@Override
	public void actualizar() {
		Iterator<Movil> consumibles = getObjetos(); 
		while(consumibles.hasNext()) {
			Consumible consumible = (Consumible) consumibles.next();
			
			if (consumible.noUsado()) {
				consumibles.remove();
				eliminar(consumible);
			}	
		}
		
		super.actualizar();
	}
	
	public void verificarColisiones(Nave nave) {
		Iterator<Movil> consumibles = getObjetos(); 
		while(consumibles.hasNext()) {
			Consumible consumible = (Consumible) consumibles.next();
			
			if (consumible.verificarColision(nave)) {
				consumible.agregarEfecto(nave);
				consumibles.remove();
				eliminar(consumible);
			}	
		}
	}
	
	private  int generarConsumibleAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
}
