package com.mygdx.game.nave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.Bala;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.FiguraForma;
import com.mygdx.game.Util;
import com.mygdx.game.b2Modelo;

public class Nave extends FiguraForma {
	private static final float anchoNave = 1.8f;
	private static final float altoNave = 2.6f;

	private static final Sound sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	private static final Sound sonidoDisparo = Gdx.audio.newSound(Gdx.files.internal("disparoNave.mp3"));

    private static final float ACELERACION = 45.f;
    
    private static final float tiempoHeridoMax = 0.5f;
    private static final float tiempoInestableMax = 1f;
    
	private int x1 = -20, y1 = -30;
	private int x2 =   0, y2 =  30;
	private int x3 =  20, y3 = -30;
	private int x4 =   0, y4 = -20;

	private int fx1 = -10, fy1 = -25;
	private int fx2 =  10, fy2 = -25;
	private int fx3 =   0, fy3 = -40;
	
    private int vidas = 3;
    private float tiempoHerido;
    private float tiempoInestable;
    private float rotacion = 18f;
    
    private DisparoNave disparoNave;
    private float tiempoMejorada;
    private boolean mejorada;
    
    public Nave(float x, float y) {
    	super(x, y, anchoNave, altoNave, BodyType.DynamicBody);
		this.getCuerpo().setLinearDamping(1.f);
		this.getCuerpo().setAngularDamping(9.f);
		this.setCollisionData(FiguraBits.NAVE.getBit(), (short) (FiguraBits.BORDE.getBit() | FiguraBits.ASTEROIDE.getBit() | 
				FiguraBits.CONSUMIBLE.getBit() | FiguraBits.BASURA_ESPACIAL.getBit()));
		
		disparoNave = new DisparoNaveComun(this);
    }
    
    @Override
 	public void dibujar(ShapeRenderer sr) {
 		 sr.begin(ShapeType.Line);
 		 sr.setColor(0xff, 0xff, 0xff, 1);

 		 sr.identity();
 		 sr.translate(getXEscala(), getYEscala(), 0);
 		 sr.rotate(0.0f, 0.0f, 1.0f, (float)Math.toDegrees(getCuerpo().getAngle()));
 		 
 		 sr.line(x1,y1,x2,y2);
 		 sr.line(x2, y2, x3, y3);
 		 sr.line(x3, y3, x4, y4);
 		 sr.line(x4, y4, x1, y1);
 		 sr.identity();

 		 sr.end();	

 		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
 			sr.begin(ShapeType.Filled);
 			sr.translate(getXEscala(), getYEscala(), 0);
 			sr.rotate(0.0f, 0.0f, 1.0f, (float)Math.toDegrees(getCuerpo().getAngle()));
 			sr.triangle(fx1, fy1, fx2, fy2, x4, y4);
 			sr.triangle(fx1, fy1, fx2, fy2, fx3, fy3);
 			sr.end();
 		}
 	}
    
    @Override
    public boolean estaDestruida() {
        return !estaHerida() && vidas == 0;
    }
    
    /** Se asegura de que Nave se mueva y cambie entre DisparoNaveComun y DisparoNaveMejorada */
    public void actualizar() {
    	if (verificarNaveHerida()) return;
    	verificarNaveInestable();
    	
    	if (mejorada) {
    		tiempoMejorada -= Gdx.graphics.getDeltaTime();
    		if (tiempoMejorada < 0) {
    			disparoNave = new DisparoNaveComun(this);
    			tiempoMejorada = 0;
    			mejorada = false;
    		}
    	}
    	
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	float fx = this.getCuerpo().getMass() * ACELERACION * (float)-Math.sin(getCuerpo().getAngle());
        	float fy = this.getCuerpo().getMass() * ACELERACION * (float)Math.cos(getCuerpo().getAngle());
        	this.getCuerpo().applyForceToCenter(fx, fy, true);


        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.getCuerpo().applyAngularImpulse(rotacion, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.getCuerpo().applyAngularImpulse(-rotacion, true);
        }
    }
    
    /** @return Bala Es el tipo de Bala que la nave debe disparar */
    public Bala disparar() {
    	return disparoNave.disparar();
    }
    
    /** Cambia el tiempo a permanecer herido de la nave, quita vida al herirse y reproduce el sonido de Nave herida. */
    public void herir() {
    	tiempoHerido = tiempoHeridoMax;
    	sonidoHerido.play();
    	quitarVida();
    }
    
    /** Verifica si Nave esta herida o congelada, y actua en consecuencia*/
    public boolean verificarNaveHerida() {
    	b2Modelo modelo = b2Modelo.getModelo();
    	
    	if (estaHerida()) {
        	if(!modelo.estaCongelado()) {
        		b2Modelo.getModelo().setCongelado(true);
        		this.congelar(false);
        		this.setVelocidad(0, 0);
        		this.setAngulo(0);
        	}
        	
    		tiempoHerido -= Gdx.graphics.getDeltaTime();
    		setX(getX() + Util.generateRandomInt(-100, 100)/1000f);
    		return true;
    	}
    	
    	if(modelo.estaCongelado()) {
    		b2Modelo.getModelo().setCongelado(false);
    	}
    	return false;
    }
    
    /** Verifica si la animaci??n de Nave herida continua o termino.
     * @return boolean: true si el tiempo de animaci??n de Nave herida no ha acabado. Si ya acabo retorna false.
     * */
    public boolean estaHerida() {
 	   return tiempoHerido > 0;
    }
    
    /** Aumenta la cantidad de vidas de la Nave en 1. */
	public void agregarVida() {
		this.vidas++;
	}
    
	/** Disminuye la cantidad de vidas de la Nave en 1. */
	public void quitarVida() {
		this.vidas--;
	}
	
	/**
	 * @return int: Cantidad de vidas que la Nave posee.
	 * */
	public int getVidas() {
		return vidas;
	}
	
	/** Cambia el dispare de la Nave DisparoNaveMejorada por el tiempo recibido. */
	public void mejorar(float tiempo) {
		disparoNave = new DisparoNaveMejorada(this);
		this.tiempoMejorada = tiempo;
		this.mejorada = true;
	}
	
	public boolean verificarNaveInestable() {
		if (estaInestable()) {
			this.getCuerpo().setAngularDamping(1f);
			tiempoInestable -= Gdx.graphics.getDeltaTime();
			rotacion = 0.5f;
			return true;
		}
		tiempoInestable = 0;
		rotacion = 18f;
		this.getCuerpo().setAngularDamping(9.f);
		return false;
	}

	public void desestabilizar() {
    	tiempoInestable = tiempoInestableMax;
    	this.getCuerpo().setAngularVelocity(20);
	}
	
	public boolean estaInestable() {
		return tiempoInestable > 0;
	}
	
	public Sound getSonidoDisparo() {
		return sonidoDisparo;
	}
	
}
