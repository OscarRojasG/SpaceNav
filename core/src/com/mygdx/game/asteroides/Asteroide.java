package com.mygdx.game.asteroides;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Bala;
import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.Nave;
import com.mygdx.game.Util;

public class Asteroide extends Enemigo {

    private AsteroideTipo tipo;
	
    public Asteroide(float x, float y, float ancho, float alto, float velX, float velY, AsteroideTipo tipo, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        float r = Util.generateRandomFloat(0, 1);
        this.getCuerpo().setAngularVelocity(r);
        this.tipo = tipo;
        this.setCollisionData(FiguraBits.ASTEROIDE.bit, (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit | FiguraBits.BORDE.bit | FiguraBits.ASTEROIDE.bit));
        System.out.println("R:" + r);

    }

    @Override
    public void setPosition(float x, float y) {
    	if (x + getAncho() > Gdx.graphics.getWidth()) {
        	x = Gdx.graphics.getWidth() - getAncho();
        	setVelocidadX(getVelocidadX() * -1);
        }
        else if (x < 0) {
        	x = 0;
        	setVelocidadX(getVelocidadX() * -1);
        }
        
        if (y + getAlto() > Gdx.graphics.getHeight()) {
        	y = Gdx.graphics.getHeight() - getAlto();
        	setVelocidadY(getVelocidadY() * -1);
        }
        else if (y < 0) {
        	y = 0;
        	setVelocidadY(getVelocidadY() * -1);
        }
        
        super.setPosition(x,y);
    }

	@Override
	public void enColisionNave(Nave nave) {
		setDestruida(true);
		explotar();
		nave.herir();
	}
	
	@Override
	public void enColisionBala(Bala bala) {
		setDestruida(true);
		bala.setDestruida(true);
		explotar();
	}
	
}
