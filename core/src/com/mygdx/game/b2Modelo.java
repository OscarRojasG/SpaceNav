package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class b2Modelo{
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera cam;
    private static Matrix4 scaled_camera = null;
	private float fraccion_frame = 0;
    private static final float SCALE = 25.f;
    
    private boolean congelado = false;

    private static b2Modelo ref = null;
    
    /** @return ref Atributo de la clase */
    public static b2Modelo getModelo() {
        if (ref == null)  ref = new b2Modelo();
        return ref;
    }
    
    /** Construye el mundo del juego */
    private b2Modelo() {
		mundo = new World(new Vector2(0.f, 0.f), false); // mundo sin gravedad
		mundo.setContactListener(new b2ContactListener());
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true); // camara debug
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
    
    /** Actualiza los frame por segundo */
	public void actualizar() {
		float dt = Gdx.graphics.getDeltaTime();
	    float frameTime = Math.min(dt, 0.25f);

	    fraccion_frame += frameTime;
	    while (fraccion_frame >= 1/60f) {
	        mundo.step(1/60f, 8, 4);
	        fraccion_frame -= 1/60f;
	    }
	}
	
	/** Ajusta la camara en el mundo */
	public void render() {
        if (scaled_camera == null) scaled_camera = cam.combined.scale(SCALE, SCALE, 1.f);
		debugRenderer.render(mundo, scaled_camera);
	}
	
	/** Obtiene la proyeccion de la camara */
	public Matrix4 getProjection() {
		return this.cam.combined;
	}
	
	/** @return SCALE Escala del b2Modelo*/
	public static float getScale() {
        return SCALE;
    }
	
	/** Crea y retorna un cuerpo para la Figura recibida */
    public Body crearCuerpo(Figura f)  {
		Body body = mundo.createBody(f.getBodyDef());

		body.setFixedRotation(false);

		f.setCuerpo(body);
		body.setUserData(f);
 
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(f.getAncho()/2, f.getAlto()/2);
 
		Fixture fixture = body.createFixture(shape, 5.f);
		fixture.setRestitution(1);

		shape.dispose();
        return body;
    }
    
    /** Destruye el Body de la Figura recibida */
	public void eliminarCuerpo(Figura f) {
		mundo.destroyBody(f.getCuerpo());
	}
	
	/** Destruye todos los Body en el World */
	public void vaciar() {
		Array<Body> bodies = new Array<Body>();
		mundo.getBodies(bodies);
		
		for (int i = 0; i < bodies.size; i++) {
			mundo.destroyBody(bodies.get(i));
		}
	}
	
	/** Dependiendo del booleano recibido, congela todos los Bodys de la pantalla
	 * @param b Es un booleano que decide si congelar. 
	 * */
	public void setCongelado(boolean b) {
		Array<Body> bodies = new Array<Body>();
		mundo.getBodies(bodies);
		
		for (int i = 0; i < bodies.size; i++) {
			bodies.get(i).setActive(!b);
		}
		
		this.congelado = b;
	}
	
	/** @return congelado Es un booleano que indica si b2Modelo esta congelado(estatico) */
	public boolean estaCongelado() {
		return congelado;
	}

}
