package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaGameOver implements Screen {
<<<<<<< HEAD
=======
	private static final Texture fondo = new Texture(Gdx.files.internal("FondoGameOver.png"));
>>>>>>> NuevaInterface
	
	private SpaceNav game;
	private SpriteBatch batch;
	
	public PantallaGameOver(SpaceNav game) {
		this.game = game; 
		batch = game.getBatch();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		batch.begin();
<<<<<<< HEAD
=======
		batch.draw(fondo, 0, 0);
>>>>>>> NuevaInterface

		game.getFont().draw(game.getBatch(), "Game Over !!! ", 120, 400,400,1,true);
		game.getFont().draw(game.getBatch(), "Presione cualquier tecla o haga click para volver al menu ...", 100, 300);
	
		batch.end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
<<<<<<< HEAD
			Screen screen = new PantallaJuego(game, 1, 0);
			game.setScreen(screen);
=======
			Screen ss = new PantallaMenu(game); // Crearemos un menu para la version final.
			game.setScreen(ss);
>>>>>>> NuevaInterface
			dispose();
		}
	}
 
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
   
}