package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;



public class Nave extends ObjetoMovil {
	private final static float anchoNave = 45;
	private final static float altoNave = 45;
	
	private final int DIRECCION_POSITIVA = 1;
	private final int DIRECCION_NEGATIVA = -1;
	private final float tiempoHeridoMax = 0.8f;
	private final float maxVel = 300;
	private final float accel = 300f;
	
	private final float anchoBala = 5;
	private final float altoBala = 20;
	private final float velBala = 150;
	private final float velDisparoSupernave = 8.5f;
	private final float anchoBalaSupernave = 10f;
	private final float altoBalaSupernave = 40;
	private final float velBalaSupernave = 350;
	
    private int vidas = 3;
    private float tiempoHerido;
    private Sound sonidoHerido;
    
    private float tiempoSupernave;
    private float tiempoUltimoDisparo;
    
    public Nave(int x, int y, Texture tx, Sound sonidoHerido) {
    	super(x, y, anchoNave, altoNave, 0, 0, tx);
    	this.sonidoHerido = sonidoHerido;
    }
    
    private float acelerar(float vel, int direction) {
    	vel = vel + accel * direction * Gdx.graphics.getDeltaTime();
    	if(Math.abs(vel) > Math.abs(maxVel)) 
    		vel = maxVel * direction;
    	
    	return vel;
    }
    
    private float calcularPosicionX() {
    	float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
    	
        if (x + getAncho() > Gdx.graphics.getWidth()) {
        	x = Gdx.graphics.getWidth() - getAncho();
        	setVelocidadX(0);
        }
        else if (x < 0) {
        	x = 0;
        	setVelocidadX(0);
        }
        
        return x;
    }
    
    private float calcularPosicionY() {
    	float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
    	
        if (y + getAlto() > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - getAlto();
        	setVelocidadY(0);
        }
        else if (y < 0) {
        	y = 0;
        	setVelocidadY(0);
        }
        
        return y;
    }
    
    private void animarNaveHerida() {
    	setX(getX() + Util.generateRandomInt(-2, 2));
    }
    
    @Override
    public void actualizar() {
    	if (estaHerida()) {
    		tiempoHerido -= Gdx.graphics.getDeltaTime();
    		animarNaveHerida();
    		return;
    	}
    	
    	if (esSupernave())
    		tiempoSupernave -= Gdx.graphics.getDeltaTime();
    	
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
        	setVelocidadX(acelerar(getVelocidadX(), DIRECCION_NEGATIVA));
        
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        	setVelocidadX(acelerar(getVelocidadX(), DIRECCION_POSITIVA));
        
    	if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) 
    		setVelocidadY(acelerar(getVelocidadY(), DIRECCION_NEGATIVA));
    	
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        	setVelocidadY(acelerar(getVelocidadY(), DIRECCION_POSITIVA));  
        
        float x = calcularPosicionX();
        float y = calcularPosicionY(); 
        setPosition(x, y);
    }
    
    public boolean disparar() {
    	if(esSupernave()) {
    		if ((tiempoUltimoDisparo - tiempoSupernave) > (1 / velDisparoSupernave)) {
    			tiempoUltimoDisparo = tiempoSupernave;
    			return true;
    		}
    		return false;
    	}
    	
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }
    
    public Bala generarBala(Texture tx) {
		float x = getX() + getAncho() / 2;
		float y = getY() + getAlto();
		
    	if (esSupernave())
    		return new Bala(x, y, anchoBalaSupernave, altoBalaSupernave, velBalaSupernave, tx);
    	
    	return new Bala(x, y, anchoBala, altoBala, velBala, tx);
    }
    
    public void herir() {
    	tiempoHerido = tiempoHeridoMax;
    	sonidoHerido.play();
    	quitarVida();
    }
    
    public void mejorar(float tiempo) {
    	tiempoSupernave = tiempo;
    	tiempoUltimoDisparo = tiempo;
    }
    
    public boolean estaDestruida() {
       return !estaHerida() && (vidas == 0);
    }
    
    public boolean estaHerida() {
 	   if (tiempoHerido > 0)
 		   return true;
 	   
 	   tiempoHerido = 0;
 	   return false;
    }
    
    public boolean esSupernave() {
  	   if (tiempoSupernave > 0)
 		   return true;
 	   
 	   tiempoSupernave = 0;
 	   return false;    	
    }
    
	public void agregarVida() {
		this.vidas++;
	}
    
	public void quitarVida() {
		this.vidas--;
	}
	
	public int getVidas() {
		return vidas;
	}
	
}
