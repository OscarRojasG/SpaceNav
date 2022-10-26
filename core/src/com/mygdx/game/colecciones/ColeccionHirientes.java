package com.mygdx.game.colecciones;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DamageNave;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;
import com.mygdx.game.Util;
import com.mygdx.game.damages.DesechoCohete;
import com.mygdx.game.damages.Satelite;

public class ColeccionHirientes {
	private final static int HIRIENTE_DESECHO_COHETE = 1;
	private final static int HIRIENTE_SATELITE = 2;
	
	private final static int Y_RESOLUTION = Gdx.graphics.getHeight()-10;
	private final static int X_RESOLUTION = Gdx.graphics.getWidth()-10;
	
	private ArrayList<DamageNave> hirientes;
	
	public ColeccionHirientes() {
		hirientes = new ArrayList<DamageNave>();
	}
	
	public void generar() {
		int p = Util.generateRandomInt(0, 9);
		if(p != 0)
			return;	
		
		int n = generarConsumibleAleatorio();
		
		DamageNave hiriente = null;
		
		int option = Util.generateRandomInt(0, 1);
		int x;
		int y;
		int velX = 200;
		int velY = 200;
		
		if (option == 0) {
			y = Util.generateRandomInt(10, Y_RESOLUTION);
			x = Util.generateRandomBetween(0, X_RESOLUTION);
			velY = 0;
			if(x != 0) {
				velX *= -1;
			}
		}
		else {
			x = Util.generateRandomInt(10, X_RESOLUTION);
			y= Util.generateRandomBetween(0, Y_RESOLUTION);
			velX = 0;
			if(y != 0) {
				velY *= -1;
			}
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
		hirientes.remove(hiriente);
	}
	
	public void dibujar(SpriteBatch batch) {
		for (int i = 0; i < hirientes.size(); i++) {
			((ObjetoEspacial)hirientes.get(i)).dibujar(batch);
		}
	}
	
	public void actualizar() {
		for (int i = 0; i < hirientes.size(); i++) {
			DamageNave hiriente = hirientes.get(i);
			
			((ObjetoEspacial)hiriente).actualizar();
			
			if(!((Hiriente)hiriente).enPantalla()) {
				eliminar(hiriente);
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
			((ObjetoEspacial)hiriente).actualizar();
			
			if (((ObjetoEspacial)hiriente).verificarColision(nave)) {
				hiriente.agregarEfecto(nave);
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
	
	private int generarConsumibleAleatorio() {
		return Util.generateRandomInt(1, 2);
	}
	
	public boolean estaVacia() {
		return hirientes.isEmpty();
	}
	
}
