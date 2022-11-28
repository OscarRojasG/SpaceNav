package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class b2Modelo {
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera cam;
    private static Matrix4 scaled_camera = null;
	private float fraccion_frame = 0;
    private static final float SCALE = 25.f;

    private static b2Modelo ref = null;

    public static b2Modelo getModelo() {
        if (ref == null)  ref = new b2Modelo();
        return ref;
    }

    private b2Modelo() {
		mundo = new World(new Vector2(0.f, 0.f), false); // mundo sin gravedad
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true); // camara debug
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void actualizar() {
		float dt = Gdx.graphics.getDeltaTime();
	    float frameTime = Math.min(dt, 0.25f);
        // System.out.println(1/dt);
        // System.out.println(dt);

	    fraccion_frame += frameTime;
	    while (fraccion_frame >= 1/60f) {
	        mundo.step(1/60f, 8, 4);
	        fraccion_frame -= 1/60f;
	    }
	}

	public void render() {
        if (scaled_camera == null) scaled_camera = cam.combined.scale(SCALE, SCALE, 1.f);
		// debugRenderer.render(mundo, scaled_camera);
		debugRenderer.render(mundo, scaled_camera);
	}

	public Matrix4 getProjection() {
		return this.cam.combined;
	}

	public static float getScale() {
        return SCALE;
    }

    public Body crearCuerpo(Figura f)  {
		Body bodyd = mundo.createBody(f.getBodyDef());

		bodyd.setFixedRotation(false);
		bodyd.setLinearDamping(1.f);
		bodyd.setAngularDamping(9.f);

		f.setCuerpo(bodyd);
 
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(f.getAncho(), f.getAlto());
 
		Fixture fixture = bodyd.createFixture(shape, 5.f);
		fixture.setRestitution(1);

		shape.dispose();
        return bodyd;
    }

}
