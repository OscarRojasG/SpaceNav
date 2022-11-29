package com.mygdx.game;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class Figura {
	private Vector2 origen;

	private float ancho;
	private float alto;

	private Body cuerpo;
	private BodyDef bodyDef;
	private short categoryBits;
	
	private boolean destruida = false;
	
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
		this(x, y, ancho, alto, BodyType.StaticBody);
	}
	
	/** Verifica si Figura ha salido de la pantalla
	 * @return boolean: Retorna false si la Figura permanece en pantalla, true en caso contrario
	 * */
	public boolean isOffscreen() {
        return false;
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
		polygon.setPosition(getXEscala(), getYEscala());
		polygon.setRotation((float)Math.toDegrees(getAngulo()));
		return polygon;
	}
	
	public float getAnchoEscala() {
        return getAncho() * b2Modelo.getScale();
    }

	public float getAltoEscala() {
        return getAlto() * b2Modelo.getScale();
    }
    
    /** Sobrescribe la posición de la Figura en el eje x por el parametro recibido.
	 * @param float: Nueva posición para la Figura respecto al eje x.
	 * */
    public void setX(float x) {
    	this.cuerpo.getPosition().set(x, getY());
    }
    
    /** Sobrescribe la posición de la Figura en el eje y por el parametro recibido.
	 * @param float: Nueva posición para la Figura respecto al eje y.
	 * */
    public void setY(float y) {
    	this.cuerpo.getPosition().set(getX(), y);
    }
    
    /** Sobrescribe la velocidad de la Figura respecto al eje x.
	 * @param float: Nueva velocidad de la Figura en el eje x.
	 * */
	public void setVelocidadX(float velX) {
		this.cuerpo.setLinearVelocity(velX, getVelocidadY());
	}
	
	/** Sobrescribe la velocidad de la Figura respecto al eje y.
	 * @param float: Nueva velocidad de la Figura en el eje y.
	 * */
	public void setVelocidadY(float velY) {
		this.cuerpo.setLinearVelocity(getVelocidadX(), velY);
	}
    
    /** Sobrescribe el tamaño de la Figura por los parametros recibidos.
	 * @param float ancho: Nuevo ancho para el tamaño de la Figura.
	 * @param float alto: Nueva altura para el tamaño de la Figura.
	 * */
    public void setTamaño(float ancho, float alto) {
    	this.ancho = ancho; 
    	this.alto = alto;
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
	
	public float getOrigenX() {
		return this.origen.x;
	}
	
	public float getOrigenY() {
		return this.origen.y;
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
			w.createBody(this.bodyDef);
		} catch(NullPointerException e) {
			throw new RuntimeException("Cuerpo sin definir");
		}
	}
	
	public void setCollisionData(short categoryBits, short maskBits) {
		Fixture fixture = getFixture();
		Filter filter = new Filter();
		filter.maskBits = maskBits;
		filter.categoryBits = categoryBits;
		fixture.setFilterData(filter);
		this.categoryBits = categoryBits;
	}
	
    public float getAngulo() {
        return this.cuerpo.getAngle();
    }
	
	public Fixture getFixture() {
		return cuerpo.getFixtureList().get(0);
	}

    public void setBodyDef(BodyDef bodyDef) {
    	this.bodyDef = bodyDef;	
	}

    public BodyDef getBodyDef() {
    	return this.bodyDef;
	}
    
    public short getCategoryBits() {
    	return categoryBits;
    }
    
    public boolean estaDestruida() {
    	return destruida;
    }
    
    public void setDestruida(boolean b) {
    	destruida = b;
    }

}

