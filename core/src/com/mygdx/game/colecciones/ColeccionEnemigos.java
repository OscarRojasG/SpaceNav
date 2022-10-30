package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Movil;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.mygdx.game.enemigos.DesechoCohete;
import com.mygdx.game.enemigos.Satelite;

public class ColeccionEnemigos extends ColeccionMovil {
	private final static int HIRIENTE_DESECHO_COHETE = 1;
	private final static int HIRIENTE_SATELITE = 2;
	
	private final static int SALIDA_HORIZONTAL = 1;
	private final static int SALIDA_VERTICAL = 2;
	
	// Limites de la pantalla.
	private final static int INICIO_PANTALLA = 0;
	private final static int FINAL_PANTALLA_VERTICAL = Gdx.graphics.getHeight();
	private final static int FINAL_PANTALLA_HORIZONTAL = Gdx.graphics.getWidth();
	
	// Limites donde puede aparecer el objeto espacial hiriente.
	private final static int INICIO_SALIDA = 10;
	private final static int FINAL_SALIDA_VERTICAL = Gdx.graphics.getHeight()-10;
	private final static int FINAL_SALIDA_HORIZONTAL = Gdx.graphics.getWidth()-10;;
	
	public void generar() {
		int p = Util.generateRandomInt(1, 20);
		if(p != 1) return;
		
		int n = generarEnemigoAleatorio();	
		Enemigo enemigo = null;
		
		int option = Util.generateRandomBetween(SALIDA_HORIZONTAL, SALIDA_VERTICAL);
		int x;
		int y;
		int velX = 200;
		int velY = 200;
		
		if (option == SALIDA_HORIZONTAL) {
			// Se decide desde que lado aparece
			x = Util.generateRandomBetween(INICIO_PANTALLA, FINAL_PANTALLA_HORIZONTAL);
			if(x != 0) velX *= -1;
			
			// Se decide en que parte del lado
			y = Util.generateRandomInt(INICIO_SALIDA, FINAL_SALIDA_VERTICAL);
			velY = 0;	
		}
		else {
			// Se decide desde que lado aparece
			y = Util.generateRandomBetween(INICIO_PANTALLA, FINAL_PANTALLA_VERTICAL);
			if(y != 0) velY *= -1;
			
			// Se decide en que parte del lado
			x = Util.generateRandomInt(INICIO_SALIDA, FINAL_SALIDA_HORIZONTAL);		
			velX = 0;
		}
		
		switch(n) {
			case HIRIENTE_DESECHO_COHETE:
				enemigo = new DesechoCohete(x, y, velX, velY);
				break;
			case HIRIENTE_SATELITE:
				enemigo = new Satelite(x, y,velX, velY);
				break;
		}
		
		agregar(enemigo);
	}
	
	public void dibujar(SpriteBatch batch) {
		Iterator<Movil> enemigos = getObjetos(); 
		while(enemigos.hasNext()) {
			Enemigo enemigo = (Enemigo) enemigos.next();
			enemigo.dibujar(batch);
		}
	}
	
	public void verificarColisiones() {
		Iterator<Movil> enemigos = getObjetos(); 
		while(enemigos.hasNext()) {
			Enemigo enemigo = (Enemigo) enemigos.next();
			Iterator<Movil> enemigos2 = getObjetos();
			
			while(enemigos2.hasNext()) {
				Enemigo enemigo2 = (Enemigo) enemigos2.next();
				
				if(enemigo != enemigo2) {
					enemigo.verificarColision(enemigo2);
				}
			}
		}
	}
	
	public void verificarColisiones(Nave nave) {
		Iterator<Movil> enemigos = getObjetos(); 
		while(enemigos.hasNext()) {
			Enemigo enemigo = (Enemigo) enemigos.next();
	    	if (enemigo.verificarColision(nave)) {
	    		nave.herir();
	    		enemigos.remove();
	    		eliminar(enemigo);
	    	}
	    }
	}
	
	private int generarEnemigoAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
}
