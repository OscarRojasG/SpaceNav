package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class Figura {
	private float ancho;
	private float alto;

	private Body cuerpo;
	private BodyDef bodyDef;
	private short categoryBits;
	
	private boolean destruida = false;
	
	public Figura(float x, float y, float ancho, float alto, BodyType type) {
		this.ancho = ancho;
		this.alto = alto;

    	BodyDef bd = new BodyDef();
    	bd.type = type;
    	bd.position.set(x, y);
    	bd.bullet = true;
    	this.setBodyDef(bd);
    	
        setCuerpo(b2Modelo.getModelo().crearCuerpo(this));
	}

	public Figura(float x, float y, float ancho, float alto) {
		this(x, y, ancho, alto, BodyType.StaticBody);
	}
	
	/** Verifica si Figura ha salido de la pantalla */
	public boolean isOffscreen() {
		float offset = 50;
		float x = getXReal();
		float y = getYReal();
		
		if (x + offset < 0 || x + getAnchoEscala() - offset > Gdx.graphics.getWidth()) {
			return true;
		}
		if (y + offset < 0 || y + getAltoEscala() - offset > Gdx.graphics.getHeight()) {
			return true;
		}
		
		return false;
    }
	
	/** Método utilizado para generar un objeto Polygon con los atributos de la clase */
	public Polygon getPoligono() {
		Polygon polygon = new Polygon(new float[] {
            -getAnchoEscala()/2, -getAltoEscala()/2,
            -getAnchoEscala()/2,  getAltoEscala()/2,
             getAnchoEscala()/2,  getAltoEscala()/2,
             getAnchoEscala()/2, -getAltoEscala()/2
        });

		polygon.setPosition(getXEscala(), getYEscala());
		polygon.setRotation((float)Math.toDegrees(getAngulo()));
		return polygon;
	}
	
	/** Obtiene el ancho del objeto en pixeles */
	public float getAnchoEscala() {
        return getAncho() * b2Modelo.getScale();
    }

	/** Obtiene el alto del objeto en pixeles */
	public float getAltoEscala() {
        return getAlto() * b2Modelo.getScale();
    }
    
    /** Establece la posición de la figura en el eje X */
    public void setX(float x) {
    	this.cuerpo.setTransform(x, getY(), getAngulo());
    }
    
    /** Establece la posición de la figura en el eje Y */
    public void setY(float y) {
    	this.cuerpo.setTransform(getX(), y, getAngulo());
    }
    
    /** Establece la posición de la figura */
    public void setPosicion(float x, float y) {
    	setX(x);
    	setY(y);
    }
    
    /** Establece la velocidad de la figura en el eje X */
	public void setVelocidadX(float velX) {
		this.cuerpo.setLinearVelocity(velX, getVelocidadY());
	}
	
	/** Establece la velocidad de la figura en el eje Y */
	public void setVelocidadY(float velY) {
		this.cuerpo.setLinearVelocity(getVelocidadX(), velY);
	}
	
	/** Establece la velocidad de la figura */
	public void setVelocidad(float velX, float velY) {
		setVelocidadX(velX);
		setVelocidadY(velY);
	}
    
    /** Establece el tamaño de la figura */
    public void setTamaño(float ancho, float alto) {
    	this.ancho = ancho; 
    	this.alto = alto;
    }
	
    /** 
	 * @return float: Posición de la figura respecto al eje X
	 * con origen en el centro de la pantalla.
	 * */
	public float getX() {
		return this.cuerpo.getPosition().x;
	}
	
	/** 
	 * @return float: Posición de la figura respecto al eje Y
	 * con origen en el centro de la pantalla.
	 * */
	public float getY() {
		return this.cuerpo.getPosition().y;
	}
	
    /** 
	 * @return float: Posición de la figura en pixeles respecto
	 * al eje X con origen en el centro de la pantalla.
	 * */
	public float getXEscala() {
		return getX() * b2Modelo.getScale();
	}
	
    /** 
	 * @return float: Posición de la figura en pixeles respecto
	 * al eje Y con origen en el centro de la pantalla.
	 * */
	public float getYEscala() {
		return getY() * b2Modelo.getScale();
	}
	
    /** 
	 * @return float: Posición de la figura en pixeles respecto
	 * al eje X con origen en el borde inferior izquierdo.
	 * */
	public float getXReal() {
		return getXEscala() + Gdx.graphics.getWidth()/2 - getAnchoEscala()/2;
	}
	
    /** 
	 * @return float: Posición de la figura en pixeles respecto
	 * al eje Y con origen en el borde inferior izquierdo.
	 * */
	public float getYReal() {
		return getYEscala() + Gdx.graphics.getHeight()/2 - getAltoEscala()/2;
	}
	
	/** 
	 * @return float: Velocidad de la figura en el eje x.
	 * */
	public float getVelocidadX() {
		return this.cuerpo.getLinearVelocity().x;
	}
	
	/** 
	 * @return float: Velocidad de la figura en el eje y.
	 * */
	public float getVelocidadY() {
		return this.cuerpo.getLinearVelocity().y;
	}
	
	/** 
	 * @return float: Ancho de la figura.
	 * */
	public float getAncho() {
		return this.ancho;
	}
	
	/** 
	 * @return float: Alto de la Figura.
	 * */
	public float getAlto() {
		return this.alto;
	}

	/**
	 * @return Body: Cuerpo de la figura usado por box2d.
	 */
	public Body getCuerpo() {
		return cuerpo;
	}
	
	/** Establece el cuerpo de la figura */
	public void setCuerpo(Body cuerpo) {
		this.cuerpo = cuerpo;
		cuerpo.setUserData(this);
	}
	
	/**
	 * Establece los datos necesarios para el manejo de colisiones.
	 * @param categoryBits: Bits asociados a esta figura.
	 * @param maskBits: Bits asociados a la figura colisionable.
	 */
	public void setCollisionData(short categoryBits, short maskBits) {
		Fixture fixture = getFixture();
		Filter filter = new Filter();
		filter.maskBits = maskBits;
		filter.categoryBits = categoryBits;
		fixture.setFilterData(filter);
		this.categoryBits = categoryBits;
	}
	
	/** Establece el ángulo de la figura */
	public void setAngulo(float angulo) {
		this.cuerpo.setAngularVelocity(angulo);
	}
	
	/**
	 * @return float: Ángulo de la figura.
	 */
    public float getAngulo() {
        return this.cuerpo.getAngle();
    }
	
    /**
     * @return Fixture: Fixture de la figura.
     */
	public Fixture getFixture() {
		return cuerpo.getFixtureList().get(0);
	}

	/** Establece la definición del cuerpo de la figura */
    public void setBodyDef(BodyDef bodyDef) {
    	this.bodyDef = bodyDef;	
	}

    /**
     * @return BodyDef: Definición del cuerpo de la figura.
     */
    public BodyDef getBodyDef() {
    	return this.bodyDef;
	}
    
    /**
     * @return short: Bits de la figura.
     */
    public short getCategoryBits() {
    	return categoryBits;
    }
    
    /**
     * @return boolean: indica si la figura debe ser eliminada
     */
    public boolean estaDestruida() {
    	return destruida;
    }
    
    /** Establece si el cuerpoo está destruido o no */
    public void setDestruida(boolean b) {
    	destruida = b;
    }
    
    /** Congela el movimiento del cuerpo */
    public void congelar(boolean b) {
    	this.cuerpo.setActive(!b);
    }

}

