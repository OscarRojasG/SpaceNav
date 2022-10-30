package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Movil;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.mygdx.game.asteroides.Asteroide;
import com.mygdx.game.asteroides.BigAsteroid;
import com.mygdx.game.asteroides.MediumAsteroid;
import com.mygdx.game.asteroides.SmallAsteroid;

public class ColeccionAsteroides extends ColeccionMovil {
	private final int ASTEROID_MIN_ANGLE = 20;
	private final int ASTEROID_MAX_ANGLE = 70;
	private final int ASTEROID_SIZE_SMALL = 1;
	private final int ASTEROID_SIZE_MEDIUM = 2;
	private final int ASTEROID_SIZE_BIG = 3;
	
	public void crear(int cantidad, int velocidad, int ronda) {
		for (int i = 0; i < cantidad; i++)
			crear(velocidad, ronda);
	}
	
	public void crear(int velocidad, int ronda) {
		float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
		angle = (float)Math.toRadians(angle);
		
		float velXAsteroides = velocidad * (float)Math.cos(angle);
		float velYAsteroides = velocidad * (float)Math.sin(angle);
		
		int size = generarAsteroideAleatorio(ronda);
		Asteroide asteroide = null;
		
		switch(size) {
			case ASTEROID_SIZE_SMALL:
				asteroide = new SmallAsteroid(velXAsteroides - 20, velYAsteroides - 20);
				break;
			case ASTEROID_SIZE_MEDIUM:
				asteroide = new MediumAsteroid(velXAsteroides, velYAsteroides);
				break;
			case ASTEROID_SIZE_BIG:
				asteroide = new BigAsteroid(velXAsteroides + 20, velYAsteroides + 20);
				break;		
		}
        
  	    this.agregar(asteroide);
	}
	
	public void dibujar(SpriteBatch batch) { 
		Iterator<Movil> asteroides = getObjetos(); 
		while(asteroides.hasNext()) {
			Asteroide asteroide = (Asteroide) asteroides.next();
			asteroide.dibujar(batch);
		}
	}
	
	public void verificarColisiones() {
		Iterator<Movil> asteroides = getObjetos(); 
		while(asteroides.hasNext()) {
			Asteroide asteroide = (Asteroide) asteroides.next();
			Iterator<Movil> asteroides2 = getObjetos();
			
			while(asteroides2.hasNext()) {
				Asteroide asteroide2 = (Asteroide) asteroides2.next();
				
				if (asteroide != asteroide2) {
					asteroide.verificarColision(asteroide2);
				}
			}
		}
	}
	
	public void verificarColisiones(Nave nave) {
		Iterator<Movil> asteroides = getObjetos(); 
		while(asteroides.hasNext()) {
			Asteroide asteroide = (Asteroide) asteroides.next();
	    	if (asteroide.verificarColision(nave)) {
	    		asteroide.agregarEfecto(nave);
	    		asteroides.remove();
	    		eliminar(asteroide);
	    	}
	    }
	}
 	
	private int generarAsteroideAleatorio(int nivel) {
		if(nivel > 20) return ASTEROID_SIZE_SMALL;
		if (nivel > 10) return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_MEDIUM);
		
		return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_BIG);
	}
	
}
