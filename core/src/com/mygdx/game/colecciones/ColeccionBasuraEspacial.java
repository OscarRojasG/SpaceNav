package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemigo;
import com.mygdx.game.Figura;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.CoheteBuilder;
import com.mygdx.game.enemigos.EnemigoBuilder;
import com.mygdx.game.enemigos.SateliteBuilder;

public class ColeccionBasuraEspacial extends ColeccionFiguraForma {
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
	private final static int FINAL_SALIDA_HORIZONTAL = Gdx.graphics.getWidth()-10;
	
	private EnemigoBuilder builder;
	
	@Override
	public void eliminarDestruidos() {
		Iterator<Figura> enemigos = getObjetos(); 
		while(enemigos.hasNext()) {
			Enemigo enemigo = (Enemigo) enemigos.next();			
			if (enemigo.estaDestruida() || enemigo.isOffscreen()) {
				enemigos.remove();
				eliminar(enemigo);
			}	
		}
	}
	
	/** Genera un elemento BasuraEspacial con lo recibido
	 * @param velocidad Es la magnitud de la velocidad que tendra BasuraEspacial
	 * */
	public void generar(int velocidad)
	{
		int p = Util.generateRandomInt(1, 100);
		
		if(p != 1) return;
		
		int n = generarEnemigoAleatorio();
		
		int option = Util.generateRandomBetween(
                SALIDA_HORIZONTAL, SALIDA_VERTICAL);

		float x;
		float y;
		float velX = velocidad;
		float velY = velocidad;
		
		if (option == SALIDA_HORIZONTAL) {
			// Se decide desde que lado aparece
			x = Util.generateRandomBetween(
                    INICIO_PANTALLA, FINAL_PANTALLA_HORIZONTAL);
			if(x != 0) velX *= -1;
			
			// Se decide en que parte del lado
			y = Util.generateRandomInt(INICIO_SALIDA, FINAL_SALIDA_VERTICAL);
			velY = 0;
		}
		else {
			// Se decide desde que lado aparece
			y = Util.generateRandomBetween(
                    INICIO_PANTALLA, FINAL_PANTALLA_VERTICAL);
			if(y != 0) velY *= -1;
			
			// Se decide en que parte del lado
			x = Util.generateRandomInt(INICIO_SALIDA, FINAL_SALIDA_HORIZONTAL);		
			velX = 0;
		}
		
		// Velocidad inicial
		Vector2 vel = new Vector2(velX, velY);
        
        x = (x - Gdx.graphics.getWidth()/2)  / (b2Modelo.getScale());
        y = (y - Gdx.graphics.getHeight()/2) / (b2Modelo.getScale());
        
        // Posicion inicial
        Vector2 pos = new Vector2(x, y);
		
		switch(n) {
			case HIRIENTE_DESECHO_COHETE:
                builder = new CoheteBuilder();
				break;
			case HIRIENTE_SATELITE:
                builder = new SateliteBuilder();
				break;
		}

        builder.setColor();
        builder.setPorte();
        builder.setPuntaje();
        builder.setPosicion(pos);
        builder.setVelocidad(vel);
		
		Enemigo enemigo = builder.build();
		agregar(enemigo);
	}
	
	private int generarEnemigoAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
}
