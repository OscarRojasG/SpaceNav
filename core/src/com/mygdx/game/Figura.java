package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Figura {
	private Vector2 posicion;
	private Vector2 velocidad;
	private Vector2 origen;

	private float ancho;
	private float alto;
	private float angulo;

	private Body cuerpo = null;
	private BodyDef body_def = null;

	public Figura(float x, float y, float ancho, float alto, BodyType type) {
		this.ancho = ancho;
		this.alto = alto;
        this.origen = new Vector2(0, 0);

    	BodyDef bd = new BodyDef();
    	bd.type = type;
    	bd.position.set(x, y);
    	this.setBodyDef(bd);
        setCuerpo(b2Modelo.getModelo().crearCuerpo(this));
	}

	public Figura(float x, float y, float ancho, float alto) {
        this(x,y, ancho, alto, BodyType.StaticBody);
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
		Polygon polygon = new Polygon(new float[]{
            -getAnchoEscala(),-getAltoEscala(),
            -getAnchoEscala(),getAltoEscala(),
            getAnchoEscala(),getAltoEscala(),
            getAnchoEscala(),-getAltoEscala()});

		polygon.setOrigin(getOrigenX(), getOrigenY());
		polygon.setRotation((float)Math.toDegrees(getAngulo()));
		polygon.setPosition(getXEscala(), getYEscala());
		return polygon;
	}
	
	public float getAnchoEscala() {
        return getAncho() * b2Modelo.getScale();
    }

	public float getAltoEscala() {
        return getAlto() * b2Modelo.getScale();
    }

    public float getAngulo() {
        return this.cuerpo.getAngle();
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
    
    public void setOrigen(float x, float y) {
    	this.origen.x = x;
    	this.origen.y = y;
    }
	
    /** 
	 * @return float: Posición de la Figura respecto al eje x.
	 * */
	public float getX() {
		return this.cuerpo.getPosition().x;
	}
	
	/** 
	 * @return float: Posición de la Figura respecto al eje y.
	 * */
	public float getY() {
		return this.cuerpo.getPosition().y;
	}

    /** 
	 * @return float: Posición de la Figura respecto al eje x.
	 * */
	public float getXEscala() {
		return this.cuerpo.getPosition().x * b2Modelo.getScale();
	}
	
	/** 
	 * @return float: Posición de la Figura respecto al eje y.
	 * */
	public float getYEscala() {
		return this.cuerpo.getPosition().y * b2Modelo.getScale();
	}
	
	/** 
	 * @return float: Velocidad de la Figura en el eje x.
	 * */
	public float getVelocidadX() {
		return this.cuerpo.getLinearVelocity().x;
	}
	
	/** 
	 * @return float: Velocidad de la Figura en el eje y.
	 * */
	public float getVelocidadY() {
		return this.cuerpo.getLinearVelocity().y;
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
		return this.getCuerpo().getAngle();
	}
	
	public float getOrigenX() {
		return this.origen.x;
	}
	
	public float getOrigenY() {
		return this.origen.y;
	}
	
	/** 
	 * @return Rectangle: Atributo clase Rectangulo con la posición (ejes x,y) y tamaño (ancho,alto) de la Figura.
	 * */
    public Rectangle getArea() {
    	return new Rectangle(this.posicion.x, this.posicion.y,
    			this.ancho, this.alto);
    }

	public Body getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(Body cuerpo) {
		this.cuerpo = cuerpo;
		cuerpo.setUserData(this);
	}

	public void crearCuerpo(World w) {
		try {
			w.createBody(this.body_def);
		} catch(NullPointerException e) {
			throw new RuntimeException("Cuerpo sin definir");
		}
	}

	public void update() {

	}


    public void setBodyDef(BodyDef bodyDef) {
    	this.body_def = bodyDef;
		
	}

    public BodyDef getBodyDef() {
    	return this.body_def;
		
	}

    public void init(BodyType type) {
    }

		

}

