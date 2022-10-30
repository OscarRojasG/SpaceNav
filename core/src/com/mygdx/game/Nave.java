package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Nave extends FiguraForma implements Movil{
	private static final int anchoNave = 40;
	private static final int altoNave = 50;

	private static final Sound sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	private static final Sound sonidoDisparo = Gdx.audio.newSound(Gdx.files.internal("disparoNave.mp3"));
	
    private float aceleracion;

	private final float velNave = 60.f;
	private final float tiempoHeridoMax = 0.8f;

	private final float accel = 6f;
	
	private final float anchoBala = 10;
	private final float altoBala = 30;
	private final float velDisparoSupernave = 8.5f;
	private final float anchoBalaSupernave = 10;
	private final float altoBalaSupernave = 40;

	int x1 = -20; int y1 = -20;
	int x2 = 0; int y2 = 40;
	int x3 = 20; int y3 = -20;
	int x4 = 0; int y4 = -10;

	int fx1 = -10; int fy1 = -15;
	int fx2 = 10; int fy2 = -15;
	int fx3 = 0; int fy3 = -30;

	
    private int vidas = 3;
    private float tiempoHerido;
    
    private float tiempoSupernave;
    private float tiempoUltimoDisparo;
    
    public Nave(int x, int y) {
    	super(x, y, anchoNave, altoNave);
    }
    
    @Override
    public void actualizar() {
    	if (estaHerida()) {
    		tiempoHerido -= Gdx.graphics.getDeltaTime();
    		animarNaveHerida();
    		return;
    	}
    	
        setVelocidadX((float)Math.sin(Math.toRadians(this.getRotacion()))* velNave);
        setVelocidadY((float)Math.cos(Math.toRadians(this.getRotacion()))* velNave);
        
    	if (esSupernave())
    		tiempoSupernave -= Gdx.graphics.getDeltaTime();
    	
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	if (this.aceleracion < 1) this.aceleracion += .16f;

        }
        else {
            if (this.aceleracion > 0) this.aceleracion -= .04f;
            else if (this.aceleracion < 0) this.aceleracion = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float a = getRotacion() - 6f;
            this.setRotacion(a);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float a = getRotacion() + 6f;
            this.setRotacion(a);
        }

        
        float x = calcularPosicionX();
        float y = calcularPosicionY(); 


        setPosition(x, y);
    }
    
    @Override
	public void dibujar(ShapeRenderer sr) {
		 Gdx.gl.glLineWidth(2.f);
		 sr.begin(ShapeType.Line);
		 sr.setColor(0xff, 0xff, 0xff, 1);

		 sr.identity();
		 sr.translate(getX() + getAncho()/2, getY() + getAlto()/2, 0);
		 sr.rotate(0.0f, 0.0f, 1.0f, -getRotacion());
		 
		 sr.line(x1,y1,x2,y2);
		 sr.line(x2, y2, x3, y3);
		 sr.line(x3, y3, x4, y4);
		 sr.line(x4, y4, x1, y1);
		 sr.identity();

		 sr.end();	

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			sr.begin(ShapeType.Filled);
			sr.translate(getX() + getAncho()/2, getY() + getAlto()/2, 0);
			sr.rotate(0.0f, 0.0f, 1.0f, -getRotacion());
			sr.triangle(fx1, fy1, fx2, fy2, x4, y4);
			sr.triangle(fx1, fy1, fx2, fy2, fx3, fy3);
			sr.end();
		}
	}
    
    /** Calcula la nueva posición en el eje x.
     * @return float: Posición de la Nave en el eje x.
     * */
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
    
    /** Calcula la nueva posición en el eje y.
     * @return float: Posición de la Nave en el eje y.
     * */
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
    
    /** Genera una animación donde el Sprite Nave simula temblar en pantalla. */
    private void animarNaveHerida() {
    	setX(getX() + Util.generateRandomInt(-2, 2));
    }
    
    /**
     * @return boolean: Retorna true si la Nave debe disparar
     * */
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
    
    /**
     * @return Bala: Genera la clase Bala (Sprite) de forma que "sale" de la nave en pantalla.
     * */
    public Bala generarBala() {
    	sonidoDisparo.play(0.01f);
    	
    	// Ubicación de la bala en el ángulo 0
        float x = getX() + getAncho()/2 - anchoBala/2;
        float y = getY() + getAlto();
        
        // Ubicación del centro de rotación respecto a la posición de la bala
        float centroX = anchoBala/2;
        float centroY = -altoNave/2;
        
        // Velocidad de la bala
        float velBalaX = getVelocidadX() * accel *  1.5f * Gdx.graphics.getDeltaTime();
        float velBalaY = getVelocidadY() * accel *  1.5f * Gdx.graphics.getDeltaTime();

    	if (esSupernave())
    		return new Bala(x, y, anchoBalaSupernave, altoBalaSupernave,
                        velBalaX * velNave, velBalaY * velNave,
                        -getRotacion(), centroX, centroY);
    	
    	return new Bala(x, y, anchoBala, altoBala,
                velBalaX * velNave * 1.5f, velBalaY * velNave * 1.5f,
                -getRotacion(), centroX, centroY);
    }
    
    /** Cambia el tiempo a permanecer herido de la nave, quita vida al herirse y reproduce el sonido de Nave herida. */
    public void herir() {
    	tiempoHerido = tiempoHeridoMax;
    	sonidoHerido.play();
    	quitarVida();
    }
    
    /** Maneja el tiempo que ha estado en accion el consumible Supernave. */
    public void mejorar(float tiempo) {
    	tiempoSupernave = tiempo;
    	tiempoUltimoDisparo = tiempo;
    }
    
    /** Verifica si al ser herida, la nave perdio su ultima vida.
     * @return boolean: true si la nave quedo con 0 vidas y su animacion herida acabo. En caso contrario false.
     * */
    public boolean estaDestruida() {
        return !estaHerida() && vidas == 0;
    }
    
    /** Verifica si la animación de Nave herida continua o termino.
     * @return boolean: true si el tiempo de animación de Nave herida no ha acabado. Si ya acabo retorna false.
     * */
    public boolean estaHerida() {
 	   if (tiempoHerido > 0)
 		   return true;
 	   
 	   tiempoHerido = 0;
 	   return false;
    }
    
    /** Verifica si lel consumible de Supernave continua o termino.
     * @return boolean: true si el tiempo del consumible Supernave no ha acabado. Si ya acabo retorna false.
     * */
    public boolean esSupernave() {
  	   if (tiempoSupernave > 0)
 		   return true;
 	   
 	   tiempoSupernave = 0;
 	   return false;    	
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
	
	/** Clase que se encarga de disminuir la velocidad general en 1 si que la Nave quede estatica. */
	public void desacelerar() {
		if(getVelocidadX() < 3) {
			return;
		}
		setVelocidadX(getVelocidadX() - 1);
		setVelocidadY(getVelocidadY() - 1);
	}
	
}