package com.mygdx.game.colecciones;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DamageNave;
import com.mygdx.game.FiguraSprite;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;
import com.mygdx.game.damages.DesechoCohete;
import com.mygdx.game.damages.Satelite;

public class ColeccionHirientes {
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
	private final static int FINAL_SALIDA_VERTICAL =Gdx.graphics.getHeight()-10;
	private final static int FINAL_SALIDA_HORIZONTAL = Gdx.graphics.getWidth()-10;;
	
	private ArrayList<DamageNave> hirientes;
	
	public ColeccionHirientes() {
		hirientes = new ArrayList<DamageNave>();
	}
	
	public void generar() {
		int p = Util.generateRandomInt(1, 20);
		if(p != 1) 
		{
			return;	
		}
		
		int n = generarHirienteAleatorio();
		
		DamageNave hiriente = null;
		
		int option = Util.generateRandomBetween(SALIDA_HORIZONTAL, SALIDA_VERTICAL);
		int x;
		int y;
		int velX = 200;
		int velY = 200;
		
		if (option == SALIDA_HORIZONTAL) {
			// Se decide desde que lado aparece
			x = Util.generateRandomBetween(INICIO_PANTALLA, FINAL_PANTALLA_HORIZONTAL);
			if(x != 0) {
				velX *= -1;
			}
			// Se decide en que parte del lado
			y = Util.generateRandomInt(INICIO_SALIDA, FINAL_SALIDA_VERTICAL);
			velY = 0;
			
		}
		else {
			// Se decide desde que lado aparece
			y = Util.generateRandomBetween(INICIO_PANTALLA, FINAL_PANTALLA_VERTICAL);
			if(y != 0) {
				velY *= -1;
			}
			
			// Se decide en que parte del lado
			x = Util.generateRandomInt(INICIO_SALIDA, FINAL_SALIDA_HORIZONTAL);		
			velX = 0;

		}
		
		switch(n) {
			case HIRIENTE_DESECHO_COHETE:
				hiriente = new DesechoCohete(x, y, velX, velY);
				break;
			case HIRIENTE_SATELITE:
				hiriente = new Satelite(x, y,velX, velY);
				break;
		}
		
		hirientes.add(hiriente);
	}
	
	public void eliminar(DamageNave hiriente) {
		eliminar(hiriente, true);
	}
	
	public void eliminar(DamageNave hiriente, boolean conSonido) {
		if (conSonido) {
			hiriente.explotar();
		}
		hirientes.remove(hiriente);
	}
	
	public void dibujar(SpriteBatch batch) {
		for (int i = 0; i < hirientes.size(); i++) {
			((FiguraSprite)hirientes.get(i)).dibujar(batch);
		}
	}
	
	public void actualizar() {
		for (int i = 0; i < hirientes.size(); i++) {
			DamageNave hiriente = hirientes.get(i);
			
			hiriente.actualizar();
			
			if(hiriente.isOffscreen()) {
				eliminar(hiriente, false);
			}
		}
	}
	
	public void verificarColisiones() {
		for (int i = 0; i < hirientes.size(); i++) {
			DamageNave a1 = hirientes.get(i);
			for (int j = i+1; j < hirientes.size(); j++) {
				DamageNave a2 = hirientes.get(j);
				a1.verificarColision(a2);
			}
		}
	}
	
	public void verificarColisiones(Nave nave) {
		for (int i = 0; i < hirientes.size(); i++) {
			DamageNave hiriente = hirientes.get(i);
			hiriente.actualizar();
			
			if (hiriente.verificarColision(nave)) {
				nave.herir();
				eliminar(hiriente);
			}
		}
	}
	
	public Iterator<DamageNave> getHirientes() {
		return hirientes.iterator();
	}
	
	public int getCantidad() {
		return hirientes.size();
	}
	
	private int generarHirienteAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
	public boolean estaVacia() {
		return hirientes.isEmpty();
	}
	
}
