package com.mygdx.game.damages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bala;
import com.mygdx.game.DamageNave;
import com.mygdx.game.Hiriente;
import com.mygdx.game.Nave;
import com.mygdx.game.ObjetoEspacial;

public class Satelite extends DamageNave implements Hiriente{
	private final static int ancho = 60;
	private final static int alto = 40;
	private final static int scoreChange = -20; // El satelite aun estaba en uso
	private final static Texture image = new Texture(Gdx.files.internal("Satelite.png"));
	
	public Satelite(float x, float y, 
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
	public boolean verificarColision(ObjetoEspacial a2) {
    	Rectangle r1 = this.getArea();
    	Rectangle r2 = a2.getArea();
    	
        if (!r1.overlaps(r2))
        	return false;
        
        float intersectionX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        float intersectionY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);
     
        if (intersectionX > intersectionY) { 	
	        if (r1.y < r2.y)
	        	setY(r1.y - intersectionY - 0.1f);
	        else
	        	setY(r1.y + intersectionY + 0.1f);
	        
	        reverseVelY();
	        a2.reverseVelY();
        }
        else {
	        if (r1.x < r2.x)
	        	this.setX(r1.x - intersectionX - 0.1f);
	        else
	        	this.setX(r1.x + intersectionX + 0.1f);
	        
	        setVelocidadX(getVelocidadX() * -1);
	        a2.setVelocidadX(getVelocidadX() * -1);
        }
        
        return true;
	}

	@Override
	public boolean verificarColision(Bala bala) {
		return this.getArea().overlaps(bala.getArea());
	}

	@Override
	public boolean enPantalla() {
		return !isOffscreen();
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
