package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector3;

public class Nave extends ObjetoMovil {
	private static final float anchoNave = 45;
	private static final float altoNave = 45;
	private static final Texture image = new Texture(Gdx.files.internal("MainShip3.png"));
	private static final Sound sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	
    private float aceleracion;
    private Vector3 collider;
    int rotacion = 0;

	private final int DIRECCION_POSITIVA = 1;
	private final int DIRECCION_NEGATIVA = -1;
	private final float VELOCIDAD = 60.f;
	private final float tiempoHeridoMax = 0.8f;
	private final float maxVel = 300;

	private final float accel = 3f;
	
	private final float anchoBala = 5;
	private final float altoBala = 20;
	private final float velDisparoSupernave = 8.5f;
	private final float anchoBalaSupernave = 10f;
	private final float altoBalaSupernave = 40;
	private final float velBalaSupernave = 350;
	
    private int vidas = 3;
    private float tiempoHerido;
    
    private float tiempoSupernave;
    private float tiempoUltimoDisparo;
    
    public Nave(int x, int y) {
    	super(x, y, anchoNave, altoNave, 0, 0, image);
    }
     
    private float calcularPosicionX() {
    	float x = getX() + getVelocidadX() * aceleracion * accel * Gdx.graphics.getDeltaTime();
    	
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
    	float y = getY() + getVelocidadY() * aceleracion * accel *  Gdx.graphics.getDeltaTime();
    	
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
    	
        setVelocidadX((float)Math.sin(Math.toRadians(this.rotacion))* VELOCIDAD);
        setVelocidadY((float)Math.cos(Math.toRadians(this.rotacion))* VELOCIDAD);
        
    	if (esSupernave())
    		tiempoSupernave -= Gdx.graphics.getDeltaTime();
    	
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	if (this.aceleracion < 1) this.aceleracion += .04f;

        }
        else {
            if (this.aceleracion > 0) this.aceleracion -= .02f;
            else if (this.aceleracion < 0) this.aceleracion = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.rotacion -=5;
            this.setRotation(-this.rotacion);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.rotacion +=5;
            this.setRotation(-this.rotacion);
        }

        
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
    
    public Bala generarBala() {
		float x = getX() + getAlto()/2 * (float)Math.sin(Math.toRadians(this.rotacion));
		float y = getY() + getAlto()/2 *  (float)Math.cos(Math.toRadians(-this.rotacion));
		
        float ballvelx = 3f * VELOCIDAD;
        float ballvely = 3f * VELOCIDAD;

    	if (esSupernave())
    		return new Bala(x, y, anchoBalaSupernave, altoBalaSupernave,
                        ballvelx * (float)Math.sin(Math.toRadians(this.rotacion)),
                        ballvely * (float)Math.cos(Math.toRadians(-this.rotacion)));
    	
    	return new Bala(x, y, anchoBala, altoBala,
                        ballvelx * (float)Math.sin(Math.toRadians(this.rotacion)),
                        ballvely * (float)Math.cos(Math.toRadians(-this.rotacion)));
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
        return !estaHerida() && vidas == 0;
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
	
	public void matarNave() {
		this.vidas = 0;
	}
	
	public int getVidas() {
		return vidas;
	}
	
}
