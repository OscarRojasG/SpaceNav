package com.mygdx.game.colecciones;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Bala;
import com.mygdx.game.DamageNave;

public class ColeccionBalas {
	private ArrayList<Bala> balas;
	
	public ColeccionBalas() {
		balas = new ArrayList<Bala>();
	}
	
	public void agregar(Bala bala) {
		balas.add(bala);
	}
	
	public void eliminar(Bala bala) {
		balas.remove(bala);
	}
	
	public void dibujar(SpriteBatch batch) {
		for (int i = 0; i < balas.size(); i++) {
			balas.get(i).dibujar(batch);
		}
	}
	
	public void actualizar() {
		for (int i = 0; i < balas.size(); i++) {
			balas.get(i).actualizar();
		}
	}
	
	public boolean verificarColisiones(DamageNave asteroide) {
		for (int i = 0; i < balas.size(); i++) {
			Bala b = balas.get(i);
			if (b.verificarColision(asteroide)) {
				eliminar(b);
				return true;
			}
		}
		return false;
	}
	
}
