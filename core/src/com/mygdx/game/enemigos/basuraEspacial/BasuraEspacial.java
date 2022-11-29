package com.mygdx.game.enemigos.basuraEspacial;

import com.mygdx.game.Bala;
import com.mygdx.game.Enemigo;
import com.mygdx.game.FiguraBits;
import com.mygdx.game.Nave;

public class BasuraEspacial extends Enemigo {
	
    public BasuraEspacial(float x, float y, float ancho, float alto, float velX, float velY, int puntaje) {
    	super(x, y, ancho, alto, velX, velY, puntaje);
        this.getCuerpo().setAngularVelocity(0.0f);
        this.setCollisionData(FiguraBits.ENEMIGO.bit,
                (short) (FiguraBits.BALA.bit | FiguraBits.NAVE.bit ));
    }

    @Override
	public void enColisionNave(Nave nave) {
		nave.desacelerar();
	}

    @Override
	public void enColisionBala(Bala bala) {
		setDestruida(true);
		bala.setDestruida(true);
		explotar();
	}
	
}
