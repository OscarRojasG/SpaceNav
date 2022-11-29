package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.asteroides.Asteroide;

public class b2ContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Figura figuraA  = (Figura) contact.getFixtureA().getBody().getUserData();
		Figura figuraB  = (Figura) contact.getFixtureB().getBody().getUserData();
		short categoryA = figuraA.getCategoryBits();
		short categoryB = figuraB.getCategoryBits();
		
		if (categoryA == FiguraBits.NAVE.bit && categoryB == FiguraBits.ASTEROIDE.bit) {
			((Asteroide) figuraB).enColisionNave((Nave) figuraA);
		}
		if (categoryA == FiguraBits.ASTEROIDE.bit && categoryB == FiguraBits.NAVE.bit) {
			((Asteroide) figuraA).enColisionNave((Nave) figuraB);
		}
		
		if (categoryA == FiguraBits.ASTEROIDE.bit && categoryB == FiguraBits.BALA.bit ) {
			((Asteroide) figuraA).enColisionBala((Bala) figuraB);
		}
		if (categoryA == FiguraBits.BALA.bit && categoryB == FiguraBits.ASTEROIDE.bit ) {
			((Asteroide) figuraB).enColisionBala((Bala) figuraA);
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
	
}
