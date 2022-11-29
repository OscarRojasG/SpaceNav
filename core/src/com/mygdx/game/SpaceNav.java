package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceNav extends Game {
	private final String nombreJuego = "Space Navigation";
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private int highScore;
	
	@Override
	public void create() {
		highScore = 0;
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(b2Modelo.getModelo().getProjection());
		
		font = new BitmapFont();
		font.getData().setScale(2f);

		ScreenUtils.clear((float)0x10/0xff, (float)0x10/0xff, (float)0x10/0xff, 1);
		Screen screen = new PantallaMenu(this);
		setScreen(screen);
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	
	/** 
	 * @return SpriteBatch: El sprite del dibujo de la Figura guardado interpretable por Batch.
	 * */
	public SpriteBatch getBatch() {
		return batch;
	}
	
	/** 
	 * @return BitmapFont: La fuente del texto para dibujar en pantalla.
	 * */
	public BitmapFont getFont() {
		return font;
	}
	
	/** 
	 * @return SpriteBatch: El sprite de la forma de la Figura guardado.
	 * */
	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
	
	/** 
	 * @return int: Puntaje más alto guardado hasta el momento.
	 * */
	public int getHighScore() {
		return highScore;
	}
	
	/** Sobrescribe el puntaje más alto guardado.
	 * @param int: Nuevo puntaje más alto alcanzado.
	 * */
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}