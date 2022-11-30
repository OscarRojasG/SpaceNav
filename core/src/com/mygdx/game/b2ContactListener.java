package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.naves.Nave;
import com.mygdx.game.consumibles.Consumible;

public class b2ContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Figura figuraA  = (Figura) contact.getFixtureA().getBody().getUserData();
		Figura figuraB  = (Figura) contact.getFixtureB().getBody().getUserData();
		short categoryA = figuraA.getCategoryBits();
		short categoryB = figuraB.getCategoryBits();
		
		if (categoryA == FiguraBits.NAVE.bit && (categoryB == FiguraBits.ASTEROIDE.bit || categoryB == FiguraBits.BASURA_ESPACIAL.bit)) {
			((Enemigo) figuraB).enColisionNave((Nave) figuraA);
		}
		if ((categoryA == FiguraBits.ASTEROIDE.bit || categoryA == FiguraBits.BASURA_ESPACIAL.bit) && categoryB == FiguraBits.NAVE.bit) {
			((Enemigo) figuraA).enColisionNave((Nave) figuraB);
		}
		
		if (categoryA == FiguraBits.BALA.bit && (categoryB == FiguraBits.ASTEROIDE.bit || categoryB == FiguraBits.BASURA_ESPACIAL.bit)) {
			((Enemigo) figuraB).enColisionBala((Bala) figuraA);
		}
		if ((categoryA == FiguraBits.ASTEROIDE.bit || categoryA == FiguraBits.BASURA_ESPACIAL.bit) && categoryB == FiguraBits.BALA.bit) {
			((Enemigo) figuraA).enColisionBala((Bala) figuraB);
		}
		
		if (categoryA == FiguraBits.NAVE.bit && categoryB == FiguraBits.CONSUMIBLE.bit) {
			((Consumible) figuraB).enColisionNave((Nave) figuraA);
			contact.setEnabled(false);
		}
		if (categoryA == FiguraBits.CONSUMIBLE.bit && categoryB == FiguraBits.NAVE.bit) {
			((Consumible) figuraA).enColisionNave((Nave) figuraB);
			contact.setEnabled(false);
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
	
}
