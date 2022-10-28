package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Figura {
	private Vector2 position;
	private Vector2 velocidad;
	private Vector2 aceleracion;

	private float width;
	private float height;
	private float angulo;
	

	public Figura(float x, float y, float width, float height) {
		this.position = new Vector2(x,y);

		this.velocidad = new Vector2(0.f,0.f);

		this.aceleracion = new Vector2(0.f,0.f);
		
		this.width = width;
		this.height = height;

		this.angulo = 0;
	}
	
	/** Verifica si Figura ha salido de la pantalla
	 * @return boolean: Retorna false si la Figura permanece en pantalla, true en caso contrario
	 * */
	public boolean isOffscreen() {
        if (getX() < 0 || getX() + getAncho() > Gdx.graphics.getWidth()) 
            return true;
        
        if (getY() < 0 || getY() + getAlto() > Gdx.graphics.getHeight())
        	return true;
        
        return false;
    }
	
	/** Sobrescribe la posición de la Figura por los parametros recibidos.
	 * @param float x: Nueva posición para la Figura respecto al eje x.
	 * @param float y: Nueva posición para la Figura respecto al eje y.
	 * */
    public void setPosition(float x, float y) {
    	this.position.x = x;
    	this.position.y = y;
    }
    
    /** Sobrescribe la posición de la Figura en el eje x por el parametro recibido.
	 * @param float: Nueva posición para la Figura respecto al eje x.
	 * */
    public void setX(float f) {
    	this.position.x = f;
    }
    
    /** Sobrescribe la posición de la Figura en el eje y por el parametro recibido.
	 * @param float: Nueva posición para la Figura respecto al eje y.
	 * */
    public void setY(float f) {
    	this.position.y = f;
    }
    
    /** Sobrescribe la velocidad de la Figura respecto al eje x.
	 * @param float: Nueva velocidad de la Figura en el eje x.
	 * */
	public void setVelocidadX(float velX) {
		this.velocidad.x = velX;
	}
	
	/** Sobrescribe la velocidad de la Figura respecto al eje y.
	 * @param float: Nueva velocidad de la Figura en el eje y.
	 * */
	public void setVelocidadY(float velY) {
		this.velocidad.y = velY;
	}
    
    
    /** Sobrescribe el tamaño de la Figura por los parametros recibidos.
	 * @param float ancho: Nuevo ancho para el tamaño de la Figura.
	 * @param float alto: Nueva altura para el tamaño de la Figura.
	 * */
    public void setSize(float ancho, float alto) {
    	this.width = ancho; 
    	this.height = alto;
    }
    
    /** Sobrescribe la rotación de la Figura en el espacio por el angulo recibidp.
	 * @param float: Nuevo ángulo en que estara rotada la Figura.
	 * */
    public void setRotation(float angle) {
    	this.setAngulo(angle);
    }
    
    /** Sobrescribe el angulo guardado en la Figura por el angulo recibidp.
	 * @param float: Nuevo ángulo en que estara rotada la Figura.
	 * */
    public void setAngulo(float angulo) {
		this.angulo = angulo;
	}
	
    /** 
	 * @return float: Posición de la Figura respecto al eje x.
	 * */
	public float getX() {
		return this.position.x;
	}
	
	/** 
	 * @return float: Posición de la Figura respecto al eje y.
	 * */
	public float getY() {
		return this.position.y;
	}
	
	/** 
	 * @return float: Ancho del tamaño de la Figura.
	 * */
	public float getAncho() {
		return this.width;
	}
	
	/** 
	 * @return float: Alto del tamaño de la Figura.
	 * */
	public float getAlto() {
		return this.height;
	}
	
	/** 
	 * @return float: Velocidad de la Figura en el eje x.
	 * */
	public float getVelocidadX() {
		return this.velocidad.x;
	}
	
	/** 
	 * @return float: Velocidad de la Figura en el eje y.
	 * */
	public float getVelocidadY() {
		return this.velocidad.y;
	}
	
	/** 
	 * @return float: Aceleración de la Figura en el eje x.
	 * */
	public float getAceleracionX() {
		return this.aceleracion.x;
	}
	
	/** 
	 * @return float: Aceleración de la Figura en el eje y.
	 * */
	public float getAceleracionY() {
		return this.aceleracion.y;
	}
	
	/** 
	 * @return float: Angulo de la Figura respecto a si mismo.
	 * */
	public float getAngulo() {
		return this.angulo;
	}
	
	/** 
	 * @return Rectangle: Atributo clase Rectangulo con la posición (ejes x,y) y tamaño (ancho,alto) de la Figura.
	 * */
    public Rectangle getArea() {
    	return new Rectangle(this.position.x, this.position.y,
    			this.width, this.height);
    }
    
    /** Clase para verificar colisión entre Figura y Figura.
	 * @param Figura: Un parametro clase Figura que contiene su posición y área
	 * @return boolean: Manda true si la posición de Enemigo coincide con el área de la Figura, false en caso contrario.
	 * */
	public boolean verificarColision(Figura figura) {
		return getArea().overlaps(figura.getArea());
	}

}

