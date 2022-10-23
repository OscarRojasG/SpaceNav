package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;



public class Nave4 {
	private final int DIRECTION_POSITIVE = 1;
	private final int DIRECTION_NEGATIVE = -1;
	private final float maxVel = 300;
	private final float accel = 300f;
	
    private float velX = 0;
    private float velY = 0;
    private int vidas = 3;
	private boolean destruida = false;
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
    }
    
    private float acelerar(float vel, int direction) {
    	vel = vel + accel * direction * Gdx.graphics.getDeltaTime();
    	if(Math.abs(vel) > Math.abs(maxVel)) 
    		vel = maxVel * direction;
    	
    	return vel;
    }
    
    private float calcularPosicionX() {
    	float x = spr.getX() + velX * Gdx.graphics.getDeltaTime();
    	
        if (x + spr.getWidth() > Gdx.graphics.getWidth()) {
        	x = Gdx.graphics.getWidth() - spr.getWidth();
        	velX = 0;
        }
        else if (x < 0) {
        	x = 0;
        	velX = 0;
        }
        
        return x;
    }
    
    private float calcularPosicionY() {
    	float y = spr.getY() + velY * Gdx.graphics.getDeltaTime();
    	
        if (y + spr.getHeight() > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - spr.getHeight();
        	velY = 0;
        }
        else if (y < 0) {
        	y = 0;
        	velY = 0;
        }
        
        return y;
    }
    
    public void draw(SpriteBatch batch, PantallaJuego juego) {        
        if (!herido) {
	        // Mover nave con teclado
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
	        	velX = acelerar(velX, DIRECTION_NEGATIVE);
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
	        	velX = acelerar(velX, DIRECTION_POSITIVE);
        	if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) 
        		velY = acelerar(velY, DIRECTION_NEGATIVE);
	        if (Gdx.input.isKeyPressed(Input.Keys.UP))
	        	velY = acelerar(velY, DIRECTION_POSITIVE);
        	
		    /* if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) spr.setRotation(++rotacion);
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) spr.setRotation(--rotacion);
	        
	        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
	        	velX -=Math.sin(Math.toRadians(rotacion));
	        	velY +=Math.cos(Math.toRadians(rotacion));
	        	System.out.println(rotacion+" - "+Math.sin(Math.toRadians(rotacion))+" - "+Math.cos(Math.toRadians(rotacion))) ;    
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
	        	velX +=Math.sin(Math.toRadians(rotacion));
	        	velY -=Math.cos(Math.toRadians(rotacion));
	        	     
	        }*/
	        
	        float x = calcularPosicionX();
	        float y = calcularPosicionY(); 
	        spr.setPosition(x, y);
	        spr.draw(batch);
	        
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
        // disparo
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
          Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,3,txBala);
	      juego.agregarBala(bala);
	      soundBala.play();
        }
       
    }
    
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
      
    public boolean checkCollision(Asteroide b) {
        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
            if (velX ==0) velX += b.getVelocityX()/2;
            if (b.getVelocityX() ==0) b.setVelocityY(b.getVelocityX() + (int)velX/2);
            velX = - velX;
            b.setVelocityX(-b.getVelocityX());
            
            if (velY ==0) velY += b.getVelocityY()/2;
            if (b.getVelocityY() ==0) b.setVelocityY(b.getVelocityY() + (int)velY/2);
            velY = - velY;
            b.setVelocityX(- b.getVelocityY());
            // despegar sprites
      /*      int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<velX) {
               spr.setX(spr.getX()+Math.signum(velX));
            }   */
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
          	    destruida = true; 
            return true;
        }
        return false;
    }
    
    public boolean estaDestruido() {
       return !herido && destruida;
    }
    public boolean estaHerido() {
 	   return herido;
    }
    
	public void quitarVida() {
		this.vidas--;
	}
    
    public int getVidas() {return vidas;}
    //public boolean isDestruida() {return destruida;}
    public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
	public void setVidas(int vidas2) {vidas = vidas2;}
}
