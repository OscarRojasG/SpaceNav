package com.mygdx.game.colecciones;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Consumible;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;
import com.mygdx.game.Util;
import com.mygdx.game.consumibles.Supernave;
import com.mygdx.game.consumibles.VidaExtra;

public class ColeccionConsumibles {
	private final static int CONSUMIBLE_VIDA = 1;
	private final static int CONSUMIBLE_SUPERNAVE = 2;
	
	private ArrayList<Consumible> consumibles;
	
	public ColeccionConsumibles() {
		consumibles = new ArrayList<Consumible>();
	}
	
	public void generar(float x, float y, float velX, float velY) {
		int p = Util.generateRandomInt(0, 9);
		if(p != 0)
			return;	
		
		int n = generarConsumibleAleatorio();
		
		Consumible consumible = null;
		
		switch(n) {
			case CONSUMIBLE_VIDA:
				consumible = new VidaExtra(x, y, 40, 40, velX, velY);
				break;
			case CONSUMIBLE_SUPERNAVE:
				consumible = new Supernave(x, y, 35, 42.24f, velX, velY);
				break;
		}
		
		consumibles.add(consumible);
	}
	
	public void eliminar(Consumible consumible) {
		consumibles.remove(consumible);
	}
	
	public void dibujar(SpriteBatch batch) {
		for (int i = 0; i < consumibles.size(); i++) {
			((ObjetoEspacial)consumibles.get(i)).dibujar(batch);
		}
	}
	
	public void actualizar() {
		for (int i = 0; i < consumibles.size(); i++) {
			((ObjetoEspacial)consumibles.get(i)).actualizar();
		}
	}
	
	public void verificarColisiones(Nave nave) {
		for (int i = 0; i < consumibles.size(); i++) {
			Consumible consumible = consumibles.get(i);
			((ObjetoEspacial)consumible).actualizar();
			
			if (((ObjetoEspacial)consumible).verificarColision(nave)) {
				consumible.agregarEfecto(nave);
				eliminar(consumible);
			} 
			else if (consumible.noUsado()) {
				eliminar(consumible);
			}
		}
	}
	
	private int generarConsumibleAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
}
