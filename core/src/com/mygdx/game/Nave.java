package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Nave extends FiguraForma {
	private static final float anchoNave = .9f;
	private static final float altoNave = 1.3f;

	private static final Sound sonidoHerido = Gdx.audio.newSound(
            Gdx.files.internal("hurt.ogg"));
	private static final Sound sonidoDisparo = Gdx.audio.newSound(
            Gdx.files.internal("disparoNave.mp3"));

    private static final float ROTACION = 18f;
    private static final float ACELERACION = 45.f;
	
	private final float anchoBala = 0.1f;
	private final float altoBala = 0.1f;
	private final float velDisparoSupernave = 8.5f;
	private final float anchoBalaSupernave = 0.2f;
	private final float altoBalaSupernave = 0.2f;

    private static final int[][] naveVertices = 
    {
        {-20, 30}, {0, 30}, {20, -30}, {0, -20}
    };

    private static final int[][] fuegoVertices =
    {
        {-10, -25}, {10, -25}, {0, -40}
    };

	
    private int vidas = 3;
    private float tiempoHerido;
    
    private float tiempoSupernave;
    private float tiempoUltimoDisparo;
    
    public Nave(int x, int y) {
    	super(x, y, anchoNave, altoNave, BodyType.DynamicBody);
		this.getCuerpo().setLinearDamping(1.f);
		this.getCuerpo().setAngularDamping(9.f);
		this.setCollisionData(
                FiguraBits.NAVE.bit,
                (short) (
                        FiguraBits.BORDE.bit |
                        FiguraBits.ASTEROIDE.bit |
                        FiguraBits.CONSUMIBLE.bit |
                        FiguraBits.BASURA_ESPACIAL.bit)
                );
    }
    

    public void actualizar() {
    	if (estaHerida()) {
    		tiempoHerido -= Gdx.graphics.getDeltaTime();
    		animarNaveHerida();
    		return;
    	}

    	
        
    	if (esSupernave())
    		tiempoSupernave -= Gdx.graphics.getDeltaTime();
    	
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	float fx = this.getCuerpo().getMass() *
                ACELERACION *
                (float)-Math.sin(getCuerpo().getAngle());

        	float fy = this.getCuerpo().getMass() *
                ACELERACION *
                (float)Math.cos(getCuerpo().getAngle());

        	this.getCuerpo().applyForceToCenter(fx, fy, true);


        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.getCuerpo().applyAngularImpulse(ROTACION, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.getCuerpo().applyAngularImpulse(-ROTACION, true);
        }
    }
    
    @Override
	public void dibujar(ShapeRenderer sr) {
		 sr.begin(ShapeType.Line);
		 sr.setColor(0xff, 0xff, 0xff, 1);

		 sr.identity();
		 sr.translate(getXEscala(), getYEscala(), 0);

		 sr.rotate(0.0f, 0.0f, 1.0f,
                 (float)Math.toDegrees(getCuerpo().getAngle()));

         sr.line(
                 naveVertices[0][0], // x1
                 naveVertices[0][1], // y1
                 naveVertices[1][0], // x2
                 naveVertices[1][1]  // y2
                 );

         sr.line(
                 naveVertices[1][0], // x2
                 naveVertices[1][1], // y2
                 naveVertices[2][0], // x3
                 naveVertices[2][1]  // y3
                 );

         sr.line(
                 naveVertices[2][0], // x3
                 naveVertices[2][1], // y3
                 naveVertices[3][0], // x4
                 naveVertices[3][1]  // y4
                 );

         sr.line(
                 naveVertices[3][0], // x4
                 naveVertices[3][1], // y4
                 naveVertices[0][0], // x0
                 naveVertices[0][1] // y0
                 );
		 
		 sr.identity();

		 sr.end();	

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			sr.begin(ShapeType.Filled);
			sr.translate(getXEscala(), getYEscala(), 0);

            // rotar en el eje z (openGL) 
			sr.rotate(0.0f, 0.0f, 1.0f,
                    (float)Math.toDegrees(getCuerpo().getAngle()));

         sr.line(
                 fuegoVertices[0][0], // fx1
                 fuegoVertices[0][1], // fy1
                 fuegoVertices[1][0], // fx2
                 fuegoVertices[1][1], // fy2
                 naveVertices[3][0], // x4
                 naveVertices[3][1]  // y4
                 );

         sr.line(
                 fuegoVertices[0][0], // fx1
                 fuegoVertices[0][1], // fy1
                 fuegoVertices[1][0], // fx2
                 fuegoVertices[1][1], // fy2
                 fuegoVertices[2][0], // x4
                 fuegoVertices[2][1]  // y4
                 );

			sr.end();
		}
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

        return Gdx.input.isKeyJustPressed(Input.Keys.Z) ||
            Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }
    
    /**
     * @return Bala: Genera la clase Bala (Sprite) de forma que "sale"
     * de la nave en pantalla.
     */
    public Bala generarBala() {
    	sonidoDisparo.play(0.01f);
    	
    	// Ubicación de la bala en el ángulo 0
        float x = getX() - getAlto() * (float)Math.sin(this.getAngulo()); 
        float y = getY() + getAlto() * (float)Math.cos(this.getAngulo()); 

    	if (esSupernave())
    		return new Bala(x, y,
                    anchoBalaSupernave, altoBalaSupernave,
                    70f, getAngulo());
    	
    	return new Bala(x, y, anchoBala, altoBala, 50f, getAngulo());
    }
    
    /** Cambia el tiempo a permanecer herido de la nave, quita vida al herirse
     * y reproduce el sonido de Nave herida. */
    public void herir() {
    	// tiempoHerido = tiempoHeridoMax;
    	sonidoHerido.play();
    	quitarVida();
    }
    
    /** Maneja el tiempo que ha estado en accion el consumible Supernave. */
    public void mejorar(float tiempo) {
    	tiempoSupernave = tiempo;
    	tiempoUltimoDisparo = tiempo;
    }
    
    /** Verifica si al ser herida, la nave perdio su ultima vida.
     * @return boolean: true si la nave quedo con 0 vidas y su animacion herida
     * acabo. En caso contrario false.
     * */
    public boolean estaDestruida() {
        return !estaHerida() && vidas == 0;
    }
    
    /** Verifica si la animación de Nave herida continua o termino.
     * @return boolean: true si el tiempo de animación de Nave herida no ha
     * acabado. Si ya acabo retorna false.
     * */
    public boolean estaHerida() {
 	   if (tiempoHerido > 0)
 		   return true;
 	   
 	   tiempoHerido = 0;
 	   return false;
    }
    
    /** Verifica si lel consumible de Supernave continua o termino.
     * @return boolean: true si el tiempo del consumible Supernave no ha
     * acabado. Si ya acabo retorna false.
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
	
	/** Clase que se encarga de disminuir la velocidad general en 1 si que la
     * Nave quede estatica. */
	public void desacelerar() {
		// if(getVelocidadX() < 3) {
		// 	return;
		// }
		// setVelocidadX(getVelocidadX() - 1);
		// setVelocidadY(getVelocidadY() - 1);
	}
	
}
