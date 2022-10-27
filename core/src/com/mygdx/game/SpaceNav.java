package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpaceNav extends Game {
	private final String nombreJuego = "Space Navigation";
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private int highScore;	

	public void create() {
		highScore = 0;
		batch = new SpriteBatch();
<<<<<<< HEAD
		shapeRenderer = new ShapeRenderer();
=======
>>>>>>> NuevaInterface
		font = new BitmapFont();
		font.getData().setScale(2f);
		
		Screen screen = new PantallaMenu(this);
		setScreen(screen);
<<<<<<< HEAD
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
=======
>>>>>>> NuevaInterface
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}