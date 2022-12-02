package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.consumibles.Consumible;
import com.mygdx.game.nave.Nave;

public class b2ContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Figura figuraA  = (Figura) contact.getFixtureA().getBody().getUserData();
		Figura figuraB  = (Figura) contact.getFixtureB().getBody().getUserData();
		short categoryA = figuraA.getCategoryBits();
		short categoryB = figuraB.getCategoryBits();
		
		if (categoryA == FiguraBits.NAVE.getBit() && (categoryB == FiguraBits.ASTEROIDE.getBit() || categoryB == FiguraBits.BASURA_ESPACIAL.getBit())) {
			((Enemigo) figuraB).enColisionNave((Nave) figuraA);
		}
		if ((categoryA == FiguraBits.ASTEROIDE.getBit() || categoryA == FiguraBits.BASURA_ESPACIAL.getBit()) && categoryB == FiguraBits.NAVE.getBit()) {
			((Enemigo) figuraA).enColisionNave((Nave) figuraB);
		}
		
		if (categoryA == FiguraBits.BALA.getBit() && (categoryB == FiguraBits.ASTEROIDE.getBit() || categoryB == FiguraBits.BASURA_ESPACIAL.getBit())) {
			((Enemigo) figuraB).enColisionBala((Bala) figuraA);
		}
		if ((categoryA == FiguraBits.ASTEROIDE.getBit() || categoryA == FiguraBits.BASURA_ESPACIAL.getBit()) && categoryB == FiguraBits.BALA.getBit()) {
			((Enemigo) figuraA).enColisionBala((Bala) figuraB);
		}
		
		if (categoryA == FiguraBits.NAVE.getBit() && categoryB == FiguraBits.CONSUMIBLE.getBit()) {
			((Consumible) figuraB).enColisionNave((Nave) figuraA);
			contact.setEnabled(false);
		}
		if (categoryA == FiguraBits.CONSUMIBLE.getBit() && categoryB == FiguraBits.NAVE.getBit()) {
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
