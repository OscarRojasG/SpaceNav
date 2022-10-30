package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Figura {
	private Vector2 posicion;
	private Vector2 velocidad;
	private Vector2 centroRotacion;

	private float ancho;
	private float alto;
	private float angulo;
	
	public Figura(float x, float y, float ancho, float alto) {
		this.posicion = new Vector2(x,y);
		this.velocidad = new Vector2(0.f,0.f);
		this.centroRotacion = new Vector2(0.f,0.f);

		this.ancho = ancho;
		this.alto = alto;

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
	
    /** Método para verificar colisión entre Figura y Figura.
	 * @param Figura: Un parametro clase Figura que contiene su posición y área
	 * @return boolean: Manda true si la posición de Enemigo coincide con el área de la Figura, false en caso contrario.
	 * */
	public boolean verificarColision(Figura figura) {
		Polygon p1 = this.getPoligono();
		Polygon p2 = figura.getPoligono();
		
		return Intersector.overlapConvexPolygons(p1, p2);
	}
	
	/** Método utilizado para generar un objeto Polygon con los atributos de la clase
	 * @return Polygon
	 */
	public Polygon getPoligono() {
		Polygon polygon = new Polygon(new float[]{0,0,ancho,0,ancho,alto,0,alto});
		polygon.setOrigin(centroRotacion.x, centroRotacion.y);
		polygon.setRotation(angulo);
		polygon.setPosition(posicion.x, posicion.y);
		return polygon;
	}
	
	/** Sobrescribe la posición de la Figura por los parametros recibidos.
	 * @param float x: Nueva posición para la Figura respecto al eje x.
	 * @param float y: Nueva posición para la Figura respecto al eje y.
	 * */
    public void setPosition(float x, float y) {
    	this.posicion.x = x;
    	this.posicion.y = y;
    }
    
    /** Sobrescribe la posición de la Figura en el eje x por el parametro recibido.
	 * @param float: Nueva posición para la Figura respecto al eje x.
	 * */
    public void setX(float x) {
    	this.posicion.x = x;
    }
    
    /** Sobrescribe la posición de la Figura en el eje y por el parametro recibido.
	 * @param float: Nueva posición para la Figura respecto al eje y.
	 * */
    public void setY(float y) {
    	this.posicion.y = y;
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
    public void setTamaño(float ancho, float alto) {
    	this.ancho = ancho; 
    	this.alto = alto;
    }
    
    /** Sobrescribe la rotación de la Figura en el espacio por el angulo recibido.
	 * @param float: Nuevo ángulo en que estara rotada la Figura.
	 * */
    public void setRotacion(float angulo) {
		this.angulo = angulo;
	}
    
    public void setCentroRotacion(float x, float y) {
    	this.centroRotacion.x = x;
    	this.centroRotacion.y = y;
    }
	
    /** 
	 * @return float: Posición de la Figura respecto al eje x.
	 * */
	public float getX() {
		return this.posicion.x;
	}
	
	/** 
	 * @return float: Posición de la Figura respecto al eje y.
	 * */
	public float getY() {
		return this.posicion.y;
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
	 * @return float: Ancho del tamaño de la Figura.
	 * */
	public float getAncho() {
		return this.ancho;
	}
	
	/** 
	 * @return float: Alto del tamaño de la Figura.
	 * */
	public float getAlto() {
		return this.alto;
	}
	
	/** 
	 * @return float: Angulo de la Figura respecto a si mismo.
	 * */
	public float getRotacion() {
		return this.angulo;
	}
	
	public float getCentroRotacionX() {
		return this.centroRotacion.x;
	}
	
	public float getCentroRotacionY() {
		return this.centroRotacion.y;
	}
	
	/** 
	 * @return Rectangle: Atributo clase Rectangulo con la posición (ejes x,y) y tamaño (ancho,alto) de la Figura.
	 * */
    public Rectangle getArea() {
    	return new Rectangle(this.posicion.x, this.posicion.y,
    			this.ancho, this.alto);
    }

}

