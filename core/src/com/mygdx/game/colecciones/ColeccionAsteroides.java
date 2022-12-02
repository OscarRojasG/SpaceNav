package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Figura;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.EnemigoBuilder;
import com.mygdx.game.enemigos.asteroides.Asteroide;
import com.mygdx.game.enemigos.asteroides.AsteroideChicoBuilder;
import com.mygdx.game.enemigos.asteroides.AsteroideGrandeBuilder;
import com.mygdx.game.enemigos.asteroides.AsteroideMedioBuilder;

public class ColeccionAsteroides extends ColeccionFiguraForma {
    private final int ASTEROID_MIN_ANGLE = -90;
    private final int ASTEROID_MAX_ANGLE = 90;
    private final int ASTEROID_SIZE_SMALL = 1;
    private final int ASTEROID_SIZE_MEDIUM = 2;
    private final int ASTEROID_SIZE_BIG = 3;

    public void crear(int cantidad, int velocidad, int ronda) {
        for (int i = 0; i < cantidad; i++)
            generar(velocidad, ronda);
    }
    
    /** Genera un elemento Asteroide con los parametros recibidos.
	 * @param velocidad Es la magnitud de la velocidad que tendra Asteroide.
	 * @param ronda Es la ronda en que esta el jugador.
	 * */
    public void generar(int velocidad, int ronda) {
        EnemigoBuilder builder = null;
        
        // Velocidad inicial
        float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
        angle = (float)Math.toRadians(angle);
        Vector2 vel = new Vector2(
                velocidad * (float)Math.sin(angle), 
                velocidad * (float)Math.cos(angle)
                );

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


        int size = generarAsteroideAleatorio(ronda);
        Asteroide asteroide = null;

        switch(size) {
            case ASTEROID_SIZE_SMALL:
                builder = new AsteroideChicoBuilder();
                break;
            case ASTEROID_SIZE_MEDIUM:
                builder = new AsteroideMedioBuilder();
                break;
            case ASTEROID_SIZE_BIG:
                builder = new AsteroideGrandeBuilder();
                break;
        }


        builder.setColor();
        builder.setPorte();
        builder.setPuntaje();
        builder.setVelocidad(vel);
        builder.setPosicion(pos);
        asteroide = (Asteroide)builder.build();
        this.agregar(asteroide);
    }
    
    public int eliminarDestruidos(ColeccionConsumibles consumibles) {
    	int puntaje = 0;
    	
		Iterator<Figura> asteroides = getObjetos(); 
		while(asteroides.hasNext()) {
			Asteroide asteroide = (Asteroide) asteroides.next();			
			if (asteroide.estaDestruida()) {
				asteroides.remove();
				eliminar(asteroide);
				puntaje += asteroide.getPuntaje();
				
				if (asteroide.getGeneraConsumible()) {
					consumibles.generar(asteroide.getX(), asteroide.getY(), 
										asteroide.getVelocidadX(),
                                        asteroide.getVelocidadY());
				}
			}	
		}
		
		return puntaje;
    }

    private int generarAsteroideAleatorio(int nivel) {
        if(nivel > 20) return ASTEROID_SIZE_SMALL;
        if(nivel > 10) return Util.generateRandomInt(
                ASTEROID_SIZE_SMALL, ASTEROID_SIZE_MEDIUM);

        return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_BIG);
    }

}
