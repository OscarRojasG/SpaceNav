package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Movil;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.basuraEspacial.BasuraBuilder;
import com.mygdx.game.enemigos.basuraEspacial.BasuraEspacial;

public class ColeccionBasuraEspacial extends ColeccionMovil {
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
	
	public void generar(int velocidad, int ronda) {
		BasuraBuilder builder = new BasuraBuilder();
		int p = Util.generateRandomInt(1, 20);
		if(p != 1) return;
		
		int n = generarEnemigoAleatorio();
		
		int option = Util.generateRandomBetween(SALIDA_HORIZONTAL, SALIDA_VERTICAL);
		int x;
		int y;
		int velX = velocidad;
		int velY = velocidad;
		
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
		
		
		// Posicion inicial
		Vector2 vel = new Vector2(
                velocidad, 
                velocidad
                );
        builder.setVelocidad(vel);
        // Posicion inicial
        Vector2 pos = new Vector2(
                Util.generateRandomFloat(
                    -Gdx.graphics.getWidth()/(2*b2Modelo.getScale()),
                    Gdx.graphics.getWidth()/(2*b2Modelo.getScale())
                    ),
                Util.generateRandomFloat(
                    -Gdx.graphics.getHeight()/(2*b2Modelo.getScale()),
                    Gdx.graphics.getHeight()/(2*b2Modelo.getScale())
                    )
                );
        builder.setPosicion(pos);
		
		switch(n) {
			case HIRIENTE_DESECHO_COHETE:
				builder.setPorte(35/b2Modelo.getScale());
				builder.setPuntaje(30);
				break;
			case HIRIENTE_SATELITE:
				builder.setPorte(25/b2Modelo.getScale());
				builder.setPuntaje(25);
				break;
		}
		
		BasuraEspacial enemigo = builder.build();
		agregar(enemigo);
	}
	
	public void dibujar(ShapeRenderer sp) {
		Iterator<Movil> enemigos = getObjetos(); 
		while(enemigos.hasNext()) {
			Enemigo enemigo = (Enemigo) enemigos.next();
			enemigo.dibujar(sp);
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
