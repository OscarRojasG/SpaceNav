package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Nave extends FiguraForma implements Movil{
	private static final int anchoNave = 45;
	private static final int altoNave = 45;

	private static final Sound sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	
    private float aceleracion;

	private final float VELOCIDAD = 60.f;
	private final float tiempoHeridoMax = 0.8f;

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
    	super(x, y, anchoNave, altoNave);
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
    	
        setVelocidadX((float)Math.sin(Math.toRadians(this.getAngulo()))* VELOCIDAD);
        setVelocidadY((float)Math.cos(Math.toRadians(this.getAngulo()))* VELOCIDAD);
        
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
            float a = getAngulo() - 5;
            System.out.println("izq");
            this.setAngulo(a);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float a = getAngulo() + 5;
            this.setAngulo(a);
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
		float x = getX() + getAlto()/2 * (float)Math.sin(Math.toRadians(this.getAngulo()));
		float y = getY() + getAlto()/2 *  (float)Math.cos(Math.toRadians(-this.getAngulo()));
		
        float ballvelx = 3f * VELOCIDAD;
        float ballvely = 3f * VELOCIDAD;

    	if (esSupernave())
    		return new Bala(x, y, anchoBalaSupernave, altoBalaSupernave,
                        ballvelx * (float)Math.sin(Math.toRadians(this.getAngulo())),
                        ballvely * (float)Math.cos(Math.toRadians(-this.getAngulo())));
    	
    	return new Bala(x, y, anchoBala, altoBala,
                        ballvelx * (float)Math.sin(Math.toRadians(this.getAngulo())),
                        ballvely * (float)Math.cos(Math.toRadians(-this.getAngulo())));
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

	@Override
	public void dibujar(ShapeRenderer sr) {
		 System.out.println(getAngulo());
		 sr.begin(ShapeType.Filled);
		 sr.setColor(0x0, 0xff, 0xff, 1);

		 sr.rotate(0.f, 0.f, 1.f, getAngulo());

		 sr.triangle(getX(), getY(),
				 getX() + getAncho(), getY(),
				 getX(),  getY() + getAlto()
				 );


		 sr.identity();

		 sr.end();	
	}
	
}
