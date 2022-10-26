package com.mygdx.game.colecciones;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Asteroide;
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
	
	private ArrayList<Asteroide> asteroides;
	
	public ColeccionAsteroides() {
		asteroides = new ArrayList<Asteroide>();
	}
	
	public void crear(int cantidad, int velocidad) {
		for (int i = 0; i < cantidad; i++)
			crear(velocidad);
	}
	
	public void crear(int velocidad) {
		float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
		angle = (float)Math.toRadians(angle);
		
		float velXAsteroides = velocidad * (float)Math.cos(angle);
		float velYAsteroides = velocidad * (float)Math.sin(angle);
		
		int size = generarTamañoAleatorio();
		
		Asteroide asteroide = null;
		
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
	
	public void eliminar(Asteroide asteroide) {
		asteroide.destruir();
		asteroides.remove(asteroide);
	}
	
	public void dibujar(SpriteBatch batch) {
		for (int i = 0; i < asteroides.size(); i++) {
			asteroides.get(i).dibujar(batch);
		}
	}
	
	public void verificarColisiones() {
		for (int i = 0; i < asteroides.size(); i++) {
			Asteroide a1 = asteroides.get(i);
			for (int j = i+1; j < asteroides.size(); j++) {
				Asteroide a2 = asteroides.get(j);
				a1.verificarColision(a2);
			}
		}
	}
	
	public void verificarColisiones(Nave nave) {
	    for (int i = 0; i < asteroides.size(); i++) {
	    	Asteroide a = asteroides.get(i);	
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
	
	public Iterator<Asteroide> getAsteroides() {
		return asteroides.iterator();
	}
 	
	private int generarTamañoAleatorio() {
		return Util.generateRandomInt(1, 3);
	}
	
}
