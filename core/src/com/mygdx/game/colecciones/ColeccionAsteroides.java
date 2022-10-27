package com.mygdx.game.colecciones;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DamageNave;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.mygdx.game.asteroides.BigAsteroid;
import com.mygdx.game.asteroides.MediumAsteroid;
import com.mygdx.game.asteroides.SmallAsteroid;

public class ColeccionAsteroides {
	private final int ASTEROID_MIN_ANGLE = 20;
	private final int ASTEROID_MAX_ANGLE = 70;
	private final int ASTEROID_SIZE_SMALL = 1;
	private final int ASTEROID_SIZE_MEDIUM = 2;
	private final int ASTEROID_SIZE_BIG = 3;
	
	private ArrayList<DamageNave> asteroides;
	
	public ColeccionAsteroides() {
		asteroides = new ArrayList<DamageNave>();
	}
	
	public void crear(int cantidad, int velocidad, int level) {
		for (int i = 0; i < cantidad; i++)
			crear(velocidad,level);
	}
	
	public void crear(int velocidad, int ronda) {
		float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
		angle = (float)Math.toRadians(angle);
		
		float velXAsteroides = velocidad * (float)Math.cos(angle);
		float velYAsteroides = velocidad * (float)Math.sin(angle);
		
		int size = generarTamañoAleatorio(ronda);
		
		DamageNave asteroide = null;
		
		switch(size) {
			case ASTEROID_SIZE_SMALL:
				asteroide = new SmallAsteroid(velXAsteroides, velYAsteroides);
				break;
			case ASTEROID_SIZE_MEDIUM:
				asteroide = new MediumAsteroid(velXAsteroides, velYAsteroides);
				break;
			case ASTEROID_SIZE_BIG:
				asteroide = new BigAsteroid(velXAsteroides, velYAsteroides);
				break;		
		}
        
  	    asteroides.add(asteroide);
	}
	
<<<<<<< HEAD
	public void eliminar(Asteroide asteroide) {
=======
	public void eliminar(DamageNave asteroide) {
>>>>>>> NuevaInterface
		asteroides.remove(asteroide);
	}
	
	public void dibujar(SpriteBatch batch) {
		for (int i = 0; i < asteroides.size(); i++) {
			asteroides.get(i).dibujar(batch);
		}
	}
	
	public void verificarColisiones() {
		for (int i = 0; i < asteroides.size(); i++) {
			DamageNave a1 = asteroides.get(i);
			for (int j = i+1; j < asteroides.size(); j++) {
				DamageNave a2 = asteroides.get(j);
				a1.verificarColision(a2);
			}
		}
	}
	
	public void verificarColisiones(Nave nave) {
	    for (int i = 0; i < asteroides.size(); i++) {
	    	DamageNave a = asteroides.get(i);	
	    	if (a.verificarColision(nave)) {
	    		nave.herir();
	    		eliminar(a);
	    	}
	    }
	}
	
	public void actualizar() {
		for (int i = 0; i < asteroides.size(); i++) {
			asteroides.get(i).actualizar();
		}
	}
	
	public boolean estaVacia() {
		return asteroides.isEmpty();
	}
	
	public Iterator<DamageNave> getAsteroides() {
		return asteroides.iterator();
	}
	
	public int getCantidad() {
		return asteroides.size();
	}
 	
	private int generarTamañoAleatorio(int nivel) {
		if(nivel > 20) {
			return ASTEROID_SIZE_SMALL;
		}
		
		if (nivel > 10) {
			return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_MEDIUM);
		}
		
		// Incluye el ASTEROID_SIZE_MEDIUM
		return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_BIG);
	}
	
}
