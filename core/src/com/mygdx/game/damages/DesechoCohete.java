package com.mygdx.game.damages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DamageNave;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;

public class DesechoCohete extends DamageNave implements Hiriente{
	private final static int ancho = 40;
	private final static int alto = 60;
	private final static int scoreChange = 20;
	private final static Texture image = new Texture(Gdx.files.internal("RocketEngine.png"));
	
	public DesechoCohete(float x, float y, 
			             float velX, float velY) {
		super(x, y, ancho, alto, velX, velY, image);
	}
	
	@Override
	public void actualizar() {
        float x = getX() + getVelocidadX() * Gdx.graphics.getDeltaTime();
        float y = getY() + getVelocidadY() * Gdx.graphics.getDeltaTime();
        
        setPosition(x,y);
	}
	
	@Override
	public void agregarEfecto(Nave nave) {
		nave.desacelerar();
	}


	@Override
	public int getScoreChange() {
		return scoreChange;
	}
}
