package com.mygdx.game;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class b2Modelo {
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera cam;
	private ArrayList<Fixture> fixtures;
	private float fraccion_frame = 0;

	public b2Modelo() {
		mundo = new World(new Vector2(0.f, 0.f), false);
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void actualizar() {
		float dt = Gdx.graphics.getDeltaTime();
	    // fixed time step
	    // max frame time to avoid spiral of death (on slow devices)
	    float frameTime = Math.min(dt, 0.25f);
	    fraccion_frame += frameTime;
	    while (fraccion_frame >= 1/60f) {
	        mundo.step(1/60f, 8, 3);
	        fraccion_frame -= 1/60f;
	    }
	}

	public void render() {
		debugRenderer.render(mundo, cam.combined);
	}

	public void importarFigura(Figura f) {
		// add it to the world
		Body bodyd = mundo.createBody(f.getBodyDef());

		bodyd.setFixedRotation(false);
		bodyd.setLinearDamping(0.f);
		bodyd.setAngularDamping(10.f);

		f.setCuerpo(bodyd);
 
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(f.getAncho(), f.getAlto());
 
		// set the properties of the object ( shape, weight, restitution(bouncyness)
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = shape;
 
		// create the physical object in our body)
		// without this our body would just be data in the world
		Fixture fixture = bodyd.createFixture(shape, .0001f);

		// we no longer use the shape object here so dispose of it.
		shape.dispose();

	}

	public Matrix4 getProjection() {
		return this.cam.combined;
	}

}
