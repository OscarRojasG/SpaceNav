package com.mygdx.game.colecciones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Figura;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;
import com.mygdx.game.enemigos.asteroides.Asteroide;
import com.mygdx.game.enemigos.asteroides.AsteroideBuilder;

public class ColeccionAsteroides extends ColeccionFiguras {
    private final int ASTEROID_MIN_ANGLE = -90;
    private final int ASTEROID_MAX_ANGLE = 90;
    private final int ASTEROID_SIZE_SMALL = 1;
    private final int ASTEROID_SIZE_MEDIUM = 2;
    private final int ASTEROID_SIZE_BIG = 3;

    public void crear(int cantidad, int velocidad, int ronda) {
        for (int i = 0; i < cantidad; i++)
            crear(velocidad, ronda);
    }

    public void crear(int velocidad, int ronda) {
        AsteroideBuilder builder = new AsteroideBuilder();
        // Velocidad inicial
        float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
        angle = (float)Math.toRadians(angle);
        Vector2 vel = new Vector2(
                velocidad * (float)Math.sin(angle), 
                velocidad * (float)Math.cos(angle)
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

        int size = generarAsteroideAleatorio(ronda);
        Asteroide asteroide = null;

        switch(size) {
            case ASTEROID_SIZE_SMALL:
                builder.setPorte( 40/b2Modelo.getScale());
                builder.setPuntaje(10);
                break;
            case ASTEROID_SIZE_MEDIUM:
                builder.setPorte( 60/b2Modelo.getScale());
                builder.setPuntaje(20);
                break;
            case ASTEROID_SIZE_BIG:
                builder.setPorte( 120/b2Modelo.getScale());
                builder.setPuntaje(30);
                break;
        }

        asteroide = builder.build();
        asteroide.setColor(Color.GRAY);
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
										asteroide.getVelocidadX(), asteroide.getVelocidadY());
				}
			}	
		}
		
		return puntaje;
    }

    public void dibujar(ShapeRenderer sp) { 
        Iterator<Figura> asteroides = getObjetos(); 
        while(asteroides.hasNext()) {
        	Asteroide asteroide = (Asteroide) asteroides.next();
        	asteroide.dibujar(sp);
        }
    }

    private int generarAsteroideAleatorio(int nivel) {
        if(nivel > 20) return ASTEROID_SIZE_SMALL;
        if(nivel > 10) return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_MEDIUM);

        return Util.generateRandomInt(ASTEROID_SIZE_SMALL, ASTEROID_SIZE_BIG);
    }

}
