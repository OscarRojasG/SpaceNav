package com.mygdx.game.consumibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FiguraSprite;
import com.mygdx.game.Movil;
import com.mygdx.game.Nave;

public abstract class Consumible extends FiguraSprite implements Movil {
	private long tiempoInicio;
	private float tiempoMaximo;
	
	public Consumible(float x, float y, float ancho, float alto, float tiempoMaximo, Texture tx) {
		super(x, y, ancho, alto, tx);
		this.tiempoMaximo = tiempoMaximo;
		this.tiempoInicio = TimeUtils.millis();
	}

	/** Clase usada para aplicar un efecto al colicionar con la nave
	 * @param nave: Recibe atributo de clase Nave para manipularlo
	 * */
	public abstract void agregarEfecto(Nave nave);
	
	public boolean noUsado() {
		float tiempo = TimeUtils.timeSinceMillis(tiempoInicio);
		tiempo = tiempo / 1000; // Consiguiendolo en segundos
		
		return (tiempo > tiempoMaximo);
	}

	public void actualizar() {
		float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        setPosition(x,y);
	}
	
}
